package com.springboot.point_of_sale.service;

import com.springboot.point_of_sale.dto.paginated.PaginatedResponseDTO;
import com.springboot.point_of_sale.dto.request.ItemRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemResponseDTO;

import java.util.List;

public interface ItemService {
    void save(ItemRequestDTO itemRequestDTO);
    PaginatedResponseDTO<List<ItemResponseDTO>> getAllItems(int page, int size);
    PaginatedResponseDTO<List<ItemResponseDTO>> getByName(String itemName, int page, int size);
}
