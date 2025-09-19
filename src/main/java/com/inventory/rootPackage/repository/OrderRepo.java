package com.inventory.rootPackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.rootPackage.model.ShoperPaid;

public interface OrderRepo extends JpaRepository<ShoperPaid, Integer>{

}
