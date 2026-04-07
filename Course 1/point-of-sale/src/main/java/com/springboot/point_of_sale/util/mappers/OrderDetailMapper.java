package com.springboot.point_of_sale.util.mappers;

import com.springboot.point_of_sale.dto.queryInterface.OrderDetailsInterface;
import com.springboot.point_of_sale.dto.request.OrderDetailsSaveDTO;
import com.springboot.point_of_sale.dto.response.OrderDetailsDTO;
import com.springboot.point_of_sale.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    List<OrderDetails> orderDetailsDTOTOEntity(List<OrderDetailsSaveDTO> orderDetailsSaveDTOList);

    @Mapping(source = "orderDate", target = "date")
    @Mapping(source = "contactNumber", target = "contactNumber")
    OrderDetailsDTO interfaceToDTO(OrderDetailsInterface orderDetailsInterface);

    List<OrderDetailsDTO> interfaceListToDTOList(List<OrderDetailsInterface> orderDetailsInterfaces);
}
