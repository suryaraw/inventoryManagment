package com.inventory.rootPackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.rootPackage.model.PaymentResponseEntity;

public interface PaymentResponseRepository extends JpaRepository<PaymentResponseEntity, Long>{

}
