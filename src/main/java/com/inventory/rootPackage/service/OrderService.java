package com.inventory.rootPackage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
	
	public List<ShoperPaid> getAll(){
		return repo.findAll();
	}
	
//	@Cacheable(value = "Orderrecieved")
	public List<ShoperPaid> getByNotYet(){
		return repo.findByDispatchStatus("NotYet");
	}
	
//	@Cacheable(value = "OrderDispatched")
	public List<ShoperPaid> getByDiapatched(){
		return repo.findByDispatchStatus("Dispatched");
	}
	
}
