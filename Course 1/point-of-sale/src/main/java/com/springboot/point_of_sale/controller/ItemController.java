package com.springboot.point_of_sale.controller;

import com.springboot.point_of_sale.dto.request.ItemRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemResponseDTO;
import com.springboot.point_of_sale.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping(path = "/save")
    public String itemSave(@RequestBody ItemRequestDTO itemRequestDTO){
        return itemService.save(itemRequestDTO);
    }

    @GetMapping(path = "/get-by-name",params = "name")
    public List<ItemResponseDTO> getByName(@RequestParam(value = "name") String itemName){
        return itemService.getByName(itemName);
    }

    @GetMapping(path = "/get-all-items")
    public List<ItemResponseDTO> getAllItems(){
        return itemService.getAllItems();
    }
}
