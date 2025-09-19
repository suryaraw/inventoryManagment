package com.inventory.rootPackage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventory.rootPackage.model.PaymentEntity;




public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>{
	
	@Query(value = "select p from PaymentEntity p where p.orderId = :orderId")
	Optional<PaymentEntity> getPaymentDetails (@Param("orderId") String orderId);

}
