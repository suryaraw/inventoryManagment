package com.inventory.rootPackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventory.rootPackage.model.Wholesaler;
import com.inventory.rootPackage.service.SupplierService;

@Controller
@RequestMapping("/sup")
public class SupplierController {

    private final ShopKeeperController shopKeeperController;
	
	@Autowired
	private SupplierService service;

    SupplierController(ShopKeeperController shopKeeperController) {
        this.shopKeeperController = shopKeeperController;
    }
	
	@GetMapping("/add")
	public String showSupplier(Model model) {
		List<Wholesaler> l= service.supplier();
		model.addAttribute("suppliers",l);
		return "addItem";
	}
}
