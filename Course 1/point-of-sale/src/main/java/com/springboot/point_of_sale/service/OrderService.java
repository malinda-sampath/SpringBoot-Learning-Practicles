package com.springboot.point_of_sale.service;

import com.springboot.point_of_sale.dto.request.OrderSaveDTO;

public interface OrderService {

    void save(OrderSaveDTO orderSaveDTO);
}
