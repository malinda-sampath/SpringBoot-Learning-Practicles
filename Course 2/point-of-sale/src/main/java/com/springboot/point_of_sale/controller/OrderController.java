package com.springboot.point_of_sale.controller;

import com.springboot.point_of_sale.dto.paginated.PaginatedResponseDTO;
import com.springboot.point_of_sale.dto.request.OrderSaveDTO;
import com.springboot.point_of_sale.dto.response.OrderDetailsDTO;
import com.springboot.point_of_sale.service.OrderService;
import com.springboot.point_of_sale.util.response.ResponseBuilder;
import com.springboot.point_of_sale.util.response.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse<Void>> orderSave(@RequestBody OrderSaveDTO orderSaveDTO){
        orderService.save(orderSaveDTO);
        return ResponseBuilder.created(
                "Order saved successfully",
                null
        );
    }

    @GetMapping(path = "/get-order-details", params = {"stateType", "page", "size"})
    public ResponseEntity<StandardResponse<PaginatedResponseDTO<List<OrderDetailsDTO>>>> getAllOrderDetails(
            @RequestParam(value = "stateType") String stateType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ) {

        if (!stateType.equalsIgnoreCase("active") && !stateType.equalsIgnoreCase("inactive")) {
            throw new IllegalArgumentException("State must be active or inactive");
        }

        boolean status = stateType.equalsIgnoreCase("active");
        PaginatedResponseDTO<List<OrderDetailsDTO>> data = orderService.getAllOrders(status, page, size);

        return ResponseBuilder.ok(
                "Get all order details",
                data
        );
    }
}
