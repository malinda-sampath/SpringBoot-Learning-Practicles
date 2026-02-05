package com.springboot.point_of_sale.util.mappers;

import com.springboot.point_of_sale.dto.request.ItemRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemResponseDTO;
import com.springboot.point_of_sale.entity.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item dtoToEntity(ItemRequestDTO itemRequestDTO);
    List<ItemResponseDTO> entityListTODTOList(List<Item> item);
}
