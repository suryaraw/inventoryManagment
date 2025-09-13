package com.inventory.rootPackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.rootPackage.model.PaymentEntity;



public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>{

}
