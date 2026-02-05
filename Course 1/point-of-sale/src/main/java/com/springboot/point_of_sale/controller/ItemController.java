package com.springboot.point_of_sale.controller;

import com.springboot.point_of_sale.dto.request.ItemRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemResponseDTO;
import com.springboot.point_of_sale.service.ItemService;
import com.springboot.point_of_sale.util.response.ResponseBuilder;
import com.springboot.point_of_sale.util.response.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse<Void>> itemSave(@RequestBody ItemRequestDTO itemRequestDTO) {
        itemService.save(itemRequestDTO);
        return ResponseBuilder.created(
                "Item saved successfully",
                null
        );
    }

    @GetMapping(path = "/get-by-name", params = "name")
    public ResponseEntity<StandardResponse<List<ItemResponseDTO>>> getByName(@RequestParam(value = "name") String itemName) {
        return ResponseBuilder.ok(
                "Get items by name",
                itemService.getByName(itemName)
        );
    }

    @GetMapping(path = "/get-all-items")
    public ResponseEntity<StandardResponse<List<ItemResponseDTO>>> getAllItems() {
        return ResponseBuilder.ok(
                "Get all customers",
                itemService.getAllItems()
        );
    }
}
