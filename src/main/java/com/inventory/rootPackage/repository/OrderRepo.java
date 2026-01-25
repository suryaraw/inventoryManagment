package com.inventory.rootPackage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventory.rootPackage.model.ShoperPaid;

public interface OrderRepo extends JpaRepository<ShoperPaid, Integer>{
	
	List<ShoperPaid> findByDispatchStatus(String dispatchStatus);
	
	@Query("SELECT s FROM ShoperPaid s WHERE s.item_id = :itemId AND s.paymentId.id = :paymentId")
	Optional<ShoperPaid> findByItemIdAndPayment(@Param("itemId") Long itemId,
	                                            @Param("paymentId") Long paymentId);


	
}
