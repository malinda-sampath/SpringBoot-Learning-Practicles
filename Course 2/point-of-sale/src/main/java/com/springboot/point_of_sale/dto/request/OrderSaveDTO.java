package com.springboot.point_of_sale.dto.request;

import com.springboot.point_of_sale.entity.Customer;
import com.springboot.point_of_sale.entity.OrderDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderSaveDTO {
    private int customerId;
    private Date date;
    private Double total;
    private List<OrderDetailsSaveDTO> orderDetailsSaveDTOList;
}
