package com.springboot.point_of_sale.service;

import com.springboot.point_of_sale.dto.paginated.PaginatedResponseDTO;
import com.springboot.point_of_sale.dto.request.OrderSaveDTO;
import com.springboot.point_of_sale.dto.response.OrderDetailsDTO;
import jakarta.validation.constraints.Max;

import java.util.List;

public interface OrderService {

    void save(OrderSaveDTO orderSaveDTO);

    PaginatedResponseDTO<List<OrderDetailsDTO>> getAllOrders(boolean status, int page, @Max(50) int size);
}
