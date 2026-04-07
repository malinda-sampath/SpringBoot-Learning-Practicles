package com.springboot.point_of_sale.dto.queryInterface;

import java.util.Date;
import java.util.List;

public interface OrderDetailsInterface {
    String getCustomerName();
    String getCustomerAddress();
    List<String> getContactNumber();
    Date getOrderDate();
    Double getTotal();
}
