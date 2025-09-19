package com.inventory.rootPackage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.model.ShoperPaid;
import com.inventory.rootPackage.repository.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepo repo;
	
	public void saveOrder(ShoperPaid order) {
		repo.save(order);
	}
}
