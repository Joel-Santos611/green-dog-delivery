package com.jestec.greendogdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jestec.greendogdelivery.model.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {

}
