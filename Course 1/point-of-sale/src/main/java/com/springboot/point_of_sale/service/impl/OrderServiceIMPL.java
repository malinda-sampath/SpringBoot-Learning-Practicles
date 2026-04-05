package com.springboot.point_of_sale.service.impl;

import com.springboot.point_of_sale.dto.request.OrderDetailsSaveDTO;
import com.springboot.point_of_sale.dto.request.OrderSaveDTO;
import com.springboot.point_of_sale.entity.Customer;
import com.springboot.point_of_sale.entity.Item;
import com.springboot.point_of_sale.entity.Order;
import com.springboot.point_of_sale.entity.OrderDetails;
import com.springboot.point_of_sale.exception.InsufficientStockException;
import com.springboot.point_of_sale.exception.NotFoundException;
import com.springboot.point_of_sale.repo.CustomerRepo;
import com.springboot.point_of_sale.repo.ItemRepo;
import com.springboot.point_of_sale.repo.OrderDetailsRepo;
import com.springboot.point_of_sale.repo.OrderRepo;
import com.springboot.point_of_sale.service.OrderService;
import com.springboot.point_of_sale.util.mappers.OrderDetailMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final ItemRepo itemRepo;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailsRepo orderDetailsRepo;

    public OrderServiceIMPL(OrderRepo orderRepo, CustomerRepo customerRepo, ItemRepo itemRepo, OrderDetailMapper orderDetailMapper, OrderDetailsRepo orderDetailsRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
        this.orderDetailMapper = orderDetailMapper;
        this.orderDetailsRepo = orderDetailsRepo;
    }

    @Override
    @Transactional
    public void save(OrderSaveDTO orderSaveDTO) {

        Customer customer = customerRepo.findById(orderSaveDTO.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        Order order = new Order();

        order.setCustomer(customer);

        List<OrderDetails> orderDetailsList = new ArrayList<>();

        double total = 0;

        for (OrderDetailsSaveDTO dto : orderSaveDTO.getOrderDetailsSaveDTOList()) {

            Item item = itemRepo.findById(dto.getItemID())
                    .orElseThrow(() -> new NotFoundException("Item not found"));

            if (item.getBalanceQty() < dto.getQty()) {
                throw new InsufficientStockException(
                        "Insufficient stock for item " + item.getItemName()
                );
            }

            double unitPrice = item.getSellingPrice();

            double amount = dto.getQty() * unitPrice;

            total += amount;

            OrderDetails orderDetails = new OrderDetails(
                    dto.getQty(),
                    unitPrice,
                    amount,
                    item,
                    order
            );

            item.setBalanceQty(
                    item.getBalanceQty() - dto.getQty()
            );

            orderDetailsList.add(orderDetails);
        }

        order.setTotal(total);

        orderRepo.save(order);

        orderDetailsRepo.saveAll(orderDetailsList);

    }
}
