package com.example.jpastudy.v2.repository;

import com.example.jpastudy.v2.dto.ItemResponseDto;
import com.example.jpastudy.v2.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * JPQL 사용
     * Query 어노테이션을 사용하여 JPQL을 직접 작성하는 방법
     */
    @Query("select i.name, i.price, i.quantity from Item i where i.name = :name")
    ItemResponseDto findByName(String name);

    /**
     * Query Method 사용
     * Query 이름으로 Query 문을 자동으로 생성하는 기능
     */
    Item findByPrice(int price);

}
