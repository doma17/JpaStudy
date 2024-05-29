package com.example.jpastudy.v2.controller;

import com.example.jpastudy.v2.dto.ItemCreateRequestDto;
import com.example.jpastudy.v2.dto.ItemResponseDto;
import com.example.jpastudy.v2.entity.Item;
import com.example.jpastudy.v2.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v2/item")
@RestController
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/name")
    public ItemResponseDto getItemByName(@RequestParam String name) {
        return itemService.getItemByName(name);
    }

    @GetMapping("/price")
    public Item getItemByPrice(@RequestParam int price) {
        return itemService.getItemByPrice(price);
    }

    @PostMapping
    public void createItem(@RequestBody ItemCreateRequestDto itemCreateRequestDto) {
        itemService.createItem(itemCreateRequestDto);
    }

}
