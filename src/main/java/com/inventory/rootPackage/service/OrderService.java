package com.inventory.rootPackage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.model.ShoperPaid;
import com.inventory.rootPackage.repository.ItemRepository;
import com.inventory.rootPackage.repository.OrderRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	
	@Autowired
	private OrderRepo repo;
	
	@Autowired
	private ItemRepository itemRepo;
	
	public void saveOrder(ShoperPaid order) {
		repo.save(order);
		log.info("Shoper paid saved in db");
	}
	
	public List<ShoperPaid> getAll(){
		log.info("retreived all shoperpaid from  db");
		return repo.findAll();
	}
	
	@Cacheable(value = "Orderrecieved")
	public List<ShoperPaid> getByNotYet(){
		log.debug("shoper paid from db | redis as notyet for approvel called");
		return repo.findByDispatchStatus("NotYet");
	}
	
	@Cacheable(value = "OrderDispatched")
	public List<ShoperPaid> getByDiapatched(){
		log.debug("shoper paid from db | redis as \"dispatched\" for approvel called");
		return repo.findByDispatchStatus("Dispatched");
	}

	
	

	    // Return grouped orders with stock info
	
	 public Map<String, List<Map<String, Object>>> getByInsufficientGroupedByBrand() {
	    List<ShoperPaid> orders = repo.findByDispatchStatus("Insufficient");
	    log.trace("from shoperpaid getting insufficient and group to specific using dto and map");
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
	
	 @Cacheable(value = "shoper", key ="#id" )
	public ShoperPaid getById(Integer id){
		 log.info("retreiving a shoerpaid sending as ShoperPaid and not as Optional");
		return repo.findById(id).get();
	}
	
}

