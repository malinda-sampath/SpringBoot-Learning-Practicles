package com.springboot.point_of_sale.service.impl;

import com.springboot.point_of_sale.dto.paginated.PaginatedResponseDTO;
import com.springboot.point_of_sale.dto.request.ItemRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemResponseDTO;
import com.springboot.point_of_sale.entity.Item;
import com.springboot.point_of_sale.repo.ItemRepo;
import com.springboot.point_of_sale.service.ItemService;
import com.springboot.point_of_sale.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    private final ItemRepo itemRepo;
    private final ModelMapper modelMapper;
    private final ItemMapper itemMapper;

    public ItemServiceIMPL(
            ItemRepo itemRepo,
            ModelMapper modelMapper,
            ItemMapper itemMapper
    ) {
        this.itemRepo = itemRepo;
        this.modelMapper = modelMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public void save(ItemRequestDTO itemRequestDTO) {
        itemRepo.save(itemMapper.dtoToEntity(itemRequestDTO));
    }

    @Override
    public PaginatedResponseDTO<List<ItemResponseDTO>> getAllItems(int page, int size) {
        Page<Item> itemPage = itemRepo.findAll(PageRequest.of(page, size));
        List<ItemResponseDTO> dtoList = itemMapper.entityListTODTOList(itemPage.getContent()); // itemPage.getContent() extracts the entity list from Page<Item>
        return new PaginatedResponseDTO<>(
                itemPage.getTotalElements(),
                itemPage.getTotalPages(),
                itemPage.getNumber(),
                itemPage.getSize(),
                dtoList
        );
    }

    @Override
    public PaginatedResponseDTO<List<ItemResponseDTO>> getByName(String itemName, int page, int size) {
        Page<Item> itemPage = itemRepo.getAllByItemNameAndActiveState(itemName, true, PageRequest.of(page, size));
        List<ItemResponseDTO> dtoList = itemMapper.entityListTODTOList(itemPage.getContent());
        return new PaginatedResponseDTO<>(
                itemPage.getTotalElements(),
                itemPage.getTotalPages(),
                itemPage.getNumber(),
                itemPage.getSize(),
                dtoList
        );
    }

    //Model Mapper
//    @Override
//    public List<ItemResponseDTO> getAllItems() {
//        List<Item> itemList = itemRepo.findAll();
//        if(!itemList.isEmpty()){
//            List<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();
//            for ( Item item : itemList ) {
//                itemResponseDTOS.add(modelMapper.map(item,ItemResponseDTO.class));
//            }
//            return itemResponseDTOS;
//        } else {
//            throw new RuntimeException("No items found");
//        }
//    }

//    @Override
//    public List<ItemResponseDTO> getByName(String itemName) {
//        List<Item> item = itemRepo.getAllByItemNameAndActiveState(itemName,true);
//        if (!item.isEmpty()) {
//            return modelMapper.map(item,new TypeToken<List<ItemResponseDTO>>(){}.getType());
//        } else {
//            throw new RuntimeException("Item is not active");
//        }
//    }
}
