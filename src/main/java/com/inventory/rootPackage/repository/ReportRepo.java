package com.inventory.rootPackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.rootPackage.model.ShoperPaid;

@Repository
public interface ReportRepo extends JpaRepository<ShoperPaid, Integer> {

}
