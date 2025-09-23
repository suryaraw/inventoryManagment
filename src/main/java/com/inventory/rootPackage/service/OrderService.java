package com.inventory.rootPackage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.model.ShoperPaid;
import com.inventory.rootPackage.repository.ItemRepository;
import com.inventory.rootPackage.repository.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepo repo;
	
	@Autowired
	private ItemRepository itemRepo;
	
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

	
	

	    // Return grouped orders with stock info
	 public Map<String, List<Map<String, Object>>> getByInsufficientGroupedByBrand() {
	    List<ShoperPaid> orders = repo.findByDispatchStatus("Insufficient");
	        return orders.stream().collect(Collectors.groupingBy(ShoperPaid::getBrand,
	                    Collectors.mapping(order -> {
	                        Map<String, Object> row = new HashMap<>();
	                        row.put("order", order);
	                        // 🔹 Lookup stock from Item table using item_id
	                        Integer stock = itemRepo.findById(order.getItem_id())
	                                .map(Item::getQuantity) // change to your field name
	                                .orElse(0);
	                        row.put("currentStock", stock);

	                        return row;
	                    }, Collectors.toList())
	                ));
	    }
	
	public ShoperPaid getById(Integer id){
		return repo.findById(id).get();
	}
	
}

