package com.springboot.point_of_sale.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsDTO {
    //customer
    private String customerName;
    private String customerAddress;
    private List<String> contactNumber;
    //order
    private Date date;
    private Double total;
}
