package com.inventory.rootPackage.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.inventory.rootPackage.controller.ReportGenerate;
import com.inventory.rootPackage.dto.PurchaseOrderDTO;
import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.model.PurchaseOrder;
import com.inventory.rootPackage.repository.ItemRepository;
import com.inventory.rootPackage.repository.OrderRepo;
import com.inventory.rootPackage.repository.PurchaseOrderRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PurchaseOrderService {

    private final ReportGenerate reportGenerate;
	
	@Autowired
	private OrderRepo suprepo;
	
	@Autowired
	private ItemAddService service;
	
	@Autowired
	private SupplierService supservice;
	
	@Autowired
	private PurchaseOrderRepo prepo;
	
	@Autowired
	private ReportGenerate asservice;
	
	@Autowired
	private ItemRepository irepo;

    PurchaseOrderService(ReportGenerate reportGenerate) {
        this.reportGenerate = reportGenerate;
    }
	
	public void modifyStatus(List<Integer> sNos) {
	    sNos.forEach(sNo -> {
	        suprepo.findById(sNo).ifPresent(order -> {
//	            order.setDispatchStatus("Requested");
	         	 order.setDispatchStatus("NotYet");
	            suprepo.save(order);
	        });
	    });
	    log.info("modifying to \"NotYet\" from rederrepo db" );
	}
	
	public String generateRestockRequestEmail(String supplierName) {
		log.info("setting a request format dynamically");
	    return "Subject: Restock Request – Insufficient Inventory for " + supplierName + " Items\n\n" +
	           "Dear " + supplierName + " Supplier,\n\n" +
	           "Please find below the list of items that are currently insufficient in our stock:" +
	           " Kindly arrange to supply the required stock at the earliest.\n\n" +
	           "Regards,\n" +
	           "Inventory Management Team";
	}

	
	@CachePut(value = "order")
	public void saveReport(String supplier,List<Long> ids,List<Integer> nQuantity,Model model) {
		List<PurchaseOrderDTO> list = new LinkedList<PurchaseOrderDTO>();
		list.clear();
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
			item.setQuantity(item.getQuantity()+quantity);
			irepo.save(item); // quatity change
			PurchaseOrderDTO tomail = new PurchaseOrderDTO();
			tomail.setCategory(item.getCategory());tomail.setItemId(item.getId());tomail.setItemName(item.getName());
			tomail.setNeeded(quantity);tomail.setStock(item.getQuantity());
			list.add(tomail);
		}
		log.trace("sending mail to supplier eith respective id with dto and saved to PurchaseOrderrepo");
		asservice.generatePdfAndSendEmail( model, list, supservice.getSupplierMail(supplier), generateRestockRequestEmail(supplier));
	}

}
