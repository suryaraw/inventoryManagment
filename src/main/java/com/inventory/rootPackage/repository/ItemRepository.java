package com.inventory.rootPackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.rootPackage.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	
}
