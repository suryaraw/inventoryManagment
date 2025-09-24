package com.inventory.rootPackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.rootPackage.service.PurchaseOrderService;

@Controller
public class PurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService service;
	
	@PostMapping("/notifyBrand/{brand}")
	public String notifyBrand( @PathVariable String brand, @RequestParam List<Long> itemIds,
							@RequestParam List<Integer> neededQuantities,@RequestParam("sNos") List<Integer> sno,Model model) {
		
		service.modifyStatus(sno);service.saveReport(brand, itemIds, neededQuantities,model);
		
	    for (int i = 0; i < itemIds.size(); i++) {
	        Long itemId = itemIds.get(i);
	        Integer neededQty = neededQuantities.get(i);

	        // Here you can call your mail service
	        System.out.println("Brand: " + brand 
	            + " | ItemId: " + itemId 
	            + " | Requesting Qty: " + neededQty);
	    }

	    // redirect back after mail is sent
	    return "redirect:/orders/insufficient";
	}

}
