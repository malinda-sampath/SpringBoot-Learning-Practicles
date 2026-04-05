package com.springboot.point_of_sale.util.mappers;

import com.springboot.point_of_sale.dto.request.OrderDetailsSaveDTO;
import com.springboot.point_of_sale.entity.OrderDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    List<OrderDetails> orderDetailsDTOTOEntity(List<OrderDetailsSaveDTO> orderDetailsSaveDTOList);
}
