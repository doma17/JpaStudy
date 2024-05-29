package com.example.jpastudy.v2.dto;

import com.example.jpastudy.v2.entity.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class ItemCreateRequestDto {

    private String name;
    private int price;
    private int quantity;

    public static Item toEntity(ItemCreateRequestDto itemCreateRequestDto) {
        return Item.builder()
                .name(itemCreateRequestDto.getName())
                .price(itemCreateRequestDto.getPrice())
                .quantity(itemCreateRequestDto.getQuantity())
                .build();
    }
}
