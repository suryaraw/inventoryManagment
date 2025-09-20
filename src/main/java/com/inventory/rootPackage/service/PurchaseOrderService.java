package com.inventory.rootPackage.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.model.PurchaseOrder;
import com.inventory.rootPackage.model.Wholesaler;
import com.inventory.rootPackage.repository.OrderRepo;
import com.inventory.rootPackage.repository.PurchaseOrderRepo;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Service
public class PurchaseOrderService {
	
	@Autowired
	private OrderRepo suprepo;
	
	@Autowired
	private ItemAddService service;
	
	@Autowired
	private SupplierService supservice;
	
	@Autowired
	private PurchaseOrderRepo prepo;
	
	public void modifyStatus(List<Integer> sNos) {
	    sNos.forEach(sNo -> {
	        suprepo.findById(sNo).ifPresent(order -> {
	            order.setDispatchStatus("Requested");
	            suprepo.save(order);
	        });
	    });
	}
	
	
	public void saveReport(String supplier,List<Long> ids,List<Integer> nQuantity) {
		Double overall =0.0;
		for(int i=0;i<ids.size();i++) {
			Item item= service.getbyId(ids.get(i));
			Integer quantity = nQuantity.get(i);
			Double total = (item.getWholesalePrice()* quantity) + ( (item.getGstRate() * (item.getWholesalePrice()* quantity))/100);
			overall += total;
			PurchaseOrder po =new PurchaseOrder();
			po.setCategory(item.getCategory());po.setItem_id(item.getId());po.setName(item.getName());
			po.setCurrentstock(item.getQuantity());po.setStockrequested(quantity);po.setPrice(item.getWholesalePrice());
			po.setTotal(total);po.setOrderDate(LocalDateTime.now());po.setSuplier(supplier);
			po.setWholesaler(supservice.getSupplier(supplier).get());
			if(i==ids.size()-1) {
				po.setOverall(overall);
			}
			prepo.save(po);
		}
	}

}
