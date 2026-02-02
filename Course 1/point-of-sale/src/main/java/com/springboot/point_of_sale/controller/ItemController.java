package com.springboot.point_of_sale.controller;

import com.springboot.point_of_sale.dto.request.ItemRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemResponseDTO;
import com.springboot.point_of_sale.service.ItemService;
import com.springboot.point_of_sale.util.response.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StandardResponse<String>> itemSave(@RequestBody ItemRequestDTO itemRequestDTO){
        itemService.save(itemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StandardResponse<>(
                        201,
                        "Item created successfully",
                        null
                ));
    }

    @GetMapping(path = "/get-by-name",params = "name")
    public ResponseEntity<StandardResponse<List<ItemResponseDTO>>> getByName(@RequestParam(value = "name") String itemName){
        List<ItemResponseDTO> data = itemService.getByNameByMapstruct(itemName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new StandardResponse<>(
                        200,
                        "Get items by name",
                        data
                ));
    }

    @GetMapping(path = "/get-all-items")
    public ResponseEntity<StandardResponse<List<ItemResponseDTO>>> getAllItems(){
        List<ItemResponseDTO> data = itemService.getAllItems();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new StandardResponse<>(
                        200,
                        "Get all customers",
                        data
                ));
    }
}
