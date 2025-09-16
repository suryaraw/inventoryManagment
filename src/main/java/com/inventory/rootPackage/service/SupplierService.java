package com.inventory.rootPackage.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.model.Wholesaler;
import com.inventory.rootPackage.repository.SupplierRepo;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierRepo suprepo;
	
	public void addSupplier() {
		if(suprepo.findAll().isEmpty()) {
			Wholesaler w=new Wholesaler(null,"Prestige","Arnold",9361688229l,"prestige@yahoo.com","Chennai",new ArrayList<Item>());
			Wholesaler w1=new Wholesaler(null,"Samsung","Kabir",9386321458l,"Kabir@Sam.com","Pune",new ArrayList<Item>());
			Wholesaler w2=new Wholesaler(null,"TATA","Chakravarthi",7502926340l,"Chakravarthi@gmail.com","Chennai",new ArrayList<Item>());
			suprepo.saveAll(Arrays.asList(w,w1,w2));
		}
	}
	
	public List<Wholesaler> supplier(){
		return suprepo.findAll();
	}
	
	public Optional<Wholesaler> getSupplier(Long id){
		return suprepo.findById(id);
	}
	
	public void saveSupplier(Wholesaler supplier){
		 suprepo.save(supplier);
	}
	
	public void dltSupplier(Long id){
		suprepo.deleteById(id);
	}
	
	public List<Wholesaler> allSupplier() {
		return suprepo.findAll();
	}
}
