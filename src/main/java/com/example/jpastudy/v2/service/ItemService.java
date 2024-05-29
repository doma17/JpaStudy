package com.example.jpastudy.v2.service;

import com.example.jpastudy.v2.dto.ItemCreateRequestDto;
import com.example.jpastudy.v2.dto.ItemResponseDto;
import com.example.jpastudy.v2.entity.Item;
import com.example.jpastudy.v2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemResponseDto getItemByName(String name) {
        return itemRepository.findByName(name);
    }

    public Item getItemByPrice(int price) {
        return itemRepository.findByPrice(price);
    }

    public void createItem(ItemCreateRequestDto itemCreateRequestDto) {
        itemRepository.save(ItemCreateRequestDto.toEntity(itemCreateRequestDto));
    }
}
