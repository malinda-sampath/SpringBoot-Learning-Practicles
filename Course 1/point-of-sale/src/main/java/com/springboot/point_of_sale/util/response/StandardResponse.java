package com.springboot.point_of_sale.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse<T> {
    private int status;
    private String message;
    private T data;
}
