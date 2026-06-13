package com.springboot.point_of_sale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsSaveDTO {
    private double qty;
    private Double amount;
    private int itemID;
}
