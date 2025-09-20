package com.inventory.rootPackage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.rootPackage.model.Wholesaler;


@Repository
public interface SupplierRepo extends JpaRepository<Wholesaler, Long> {
	
	Optional<Wholesaler> findByName(String name);
}
