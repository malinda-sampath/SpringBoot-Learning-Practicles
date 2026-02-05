package com.springboot.point_of_sale.service;

import com.springboot.point_of_sale.dto.request.ItemRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemResponseDTO;

import java.util.List;

public interface ItemService {
    void save(ItemRequestDTO itemRequestDTO);
    List<ItemResponseDTO> getAllItems();
    List<ItemResponseDTO> getByName(String itemName);
}
