package com.springboot.point_of_sale.controller;

import com.springboot.point_of_sale.dto.paginated.PaginatedResponseDTO;
import com.springboot.point_of_sale.dto.request.ItemRequestDTO;
import com.springboot.point_of_sale.dto.response.ItemResponseDTO;
import com.springboot.point_of_sale.service.ItemService;
import com.springboot.point_of_sale.util.response.ResponseBuilder;
import com.springboot.point_of_sale.util.response.StandardResponse;
import jakarta.validation.constraints.Max;
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

    @GetMapping(path = "/get-by-name", params = {"name", "page", "size"})
    public ResponseEntity<StandardResponse<PaginatedResponseDTO<List<ItemResponseDTO>>>> getByName(
            @RequestParam(value = "name") String itemName,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        PaginatedResponseDTO<List<ItemResponseDTO>> data = itemService.getByName(itemName, page, size);
        return ResponseBuilder.ok(
                "Get items by name",
                data
        );
    }

    @GetMapping(path = "/get-all-items", params = {"page", "size"})
    public ResponseEntity<StandardResponse<PaginatedResponseDTO<List<ItemResponseDTO>>>> getAllItems(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ) {
        PaginatedResponseDTO<List<ItemResponseDTO>> data = itemService.getAllItems(page, size);
        return ResponseBuilder.ok(
                "Get all customers",
                data
        );
    }
}
