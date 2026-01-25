package com.inventory.rootPackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.rootPackage.model.PurchaseOrder;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long>{

}
