package com.inventory.rootPackage.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.model.Wholesaler;
import com.inventory.rootPackage.repository.SupplierRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SupplierService {
	
	@Autowired
	private SupplierRepo suprepo;
	
	public void addSupplier() {
		if(suprepo.findAll().isEmpty()) {
			Wholesaler w=new Wholesaler(null,"Prestige","Arnold",9361688229l,"prestige@yahoo.com","Chennai",new ArrayList<Item>());
			Wholesaler w1=new Wholesaler(null,"Samsung","Kabir",9386321458l,"Kabir@Sam.com","Pune",new ArrayList<Item>());
			Wholesaler w2=new Wholesaler(null,"TATA","Chakravarthi",7502926340l,"Chakravarthi@gmail.com","Chennai",new ArrayList<Item>());
			suprepo.saveAll(Arrays.asList(w,w1,w2));
			log.info("supplier added manually to Wholesaler");
		}
	}
	
////	@Cacheable(value = "suppliers" )
//	public List<Wholesaler> supplier(){
//		return suprepo.findAll();
//	}
//	
////	@Cacheable(value = "supplier" , key = "#id")
//	public Optional<Wholesaler> getSupplier(Long id){
//		return suprepo.findById(id);
//	}
//	
////	@CachePut(value = "supplier" , key = "#supplier.id")
//	public void saveSupplier(Wholesaler supplier){
//		 suprepo.save(supplier);
//	}
//	
////	@CacheEvict(value = "supplier" , key = "#id")
//	public void dltSupplier(Long id){
//		suprepo.deleteById(id);
//	}
//	@Cacheable(value = "suppliers")
//	public List<Wholesaler> allSupplier() {
//		return suprepo.findAll();
//	}
//	
////	@Cacheable(value = "supplier" , key = "#name")
//	public Optional<Wholesaler> getSupplier(String name){
//		return suprepo.findByName(name);
//	}
//	
////	@Cacheable(value = "supplier" ,key = "#mail")
//	public String getSupplierMail(String name) {
//		return suprepo.findByName(name).get().getEmail();
//	}
	
	 public List<Wholesaler> supplier() {
	        log.info("Fetching all suppliers");
	        List<Wholesaler> result = suprepo.findAll();
	        log.info("Fetched {} suppliers", result.size());
	        return result;
	    }

	    // @Cacheable(value = "supplier", key = "#id")
	    public Optional<Wholesaler> getSupplier(Long id) {
	        log.info("Fetching supplier by id: {}", id);
	        Optional<Wholesaler> result = suprepo.findById(id);
	        if(result.isPresent()) {
	            log.info("Supplier found: {}", result.get().getName());
	        } else {
	            log.warn("No supplier found with id: {}", id);
	        }
	        return result;
	    }

	    // @CachePut(value = "supplier", key = "#supplier.id")
	    public void saveSupplier(Wholesaler supplier) {
	        log.info("Saving supplier: {}", supplier.getName());
	        suprepo.save(supplier);
	        log.info("Supplier saved with id: {}", supplier.getId());
	    }

	    // @CacheEvict(value = "supplier", key = "#id")
	    public void dltSupplier(Long id) {
	        log.info("Deleting supplier with id: {}", id);
	        suprepo.deleteById(id);
	        log.info("Supplier deleted with id: {}", id);
	    }

	    @Cacheable(value = "suppliers")
	    public List<Wholesaler> allSupplier() {
	        log.info("Fetching all suppliers (cached version)");
	        List<Wholesaler> result = suprepo.findAll();
	        log.info("Fetched {} suppliers", result.size());
	        return result;
	    }

	    // @Cacheable(value = "supplier", key = "#name")
	    public Optional<Wholesaler> getSupplier(String name) {
	        log.info("Fetching supplier by name: {}", name);
	        Optional<Wholesaler> result = suprepo.findByName(name);
	        if(result.isPresent()) {
	            log.info("Supplier found: {}", result.get().getName());
	        } else {
	            log.warn("No supplier found with name: {}", name);
	        }
	        return result;
	    }

	    // @Cacheable(value = "supplier", key = "#mail")
	    public String getSupplierMail(String name) {
	        log.info("Fetching email for supplier: {}", name);
	        Optional<Wholesaler> supplierOpt = suprepo.findByName(name);
	        if(supplierOpt.isPresent()) {
	            String email = supplierOpt.get().getEmail();
	            log.info("Email for supplier {}: {}", name, email);
	            return email;
	        } else {
	            log.warn("No supplier found with name: {}", name);
	            return null;
	        }
	    }
}
