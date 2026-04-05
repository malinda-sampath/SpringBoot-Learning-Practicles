package com.springboot.point_of_sale.controller;

import com.springboot.point_of_sale.dto.request.OrderSaveDTO;
import com.springboot.point_of_sale.service.OrderService;
import com.springboot.point_of_sale.util.response.ResponseBuilder;
import com.springboot.point_of_sale.util.response.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
