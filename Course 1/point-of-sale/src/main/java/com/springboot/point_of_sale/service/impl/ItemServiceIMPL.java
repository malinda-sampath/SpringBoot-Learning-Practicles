package com.springboot.point_of_sale.service.impl;

import com.springboot.point_of_sale.dto.request.ItemRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemResponseDTO;
import com.springboot.point_of_sale.entity.Item;
import com.springboot.point_of_sale.repo.ItemRepo;
import com.springboot.point_of_sale.service.ItemService;
import com.sun.jdi.request.DuplicateRequestException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    private final ItemRepo itemRepo;
    private final ModelMapper modelMapper;

    public ItemServiceIMPL(ItemRepo itemRepo,ModelMapper modelMapper) {
        this.itemRepo = itemRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public String save(ItemRequestDTO itemRequestDTO) {
        Item item = modelMapper.map(itemRequestDTO,Item.class);
        if (!itemRepo.existsById(item.getItemID())){
            itemRepo.save(item);
            return "Item saved";
        } else {
            throw new DuplicateRequestException("Item already exist");
        }
    }

    @Override
    public List<ItemResponseDTO> getAllItems() {
        List<Item> itemList = itemRepo.findAll();
        if(!itemList.isEmpty()){
            List<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();
            for ( Item item : itemList ) {
                itemResponseDTOS.add(modelMapper.map(item,ItemResponseDTO.class));
            }
            return itemResponseDTOS;
        } else {
            throw new RuntimeException("No items found");
        }
    }

    @Override
    public List<ItemResponseDTO> getByName(String itemName) {
        List<Item> item = itemRepo.getAllByItemNameAndActiveState(itemName,true);
        if (!item.isEmpty()) {
            return modelMapper.map(item,new TypeToken<List<ItemResponseDTO>>(){}.getType());
        } else {
            throw new RuntimeException("Item is not active");
        }
    }
}
