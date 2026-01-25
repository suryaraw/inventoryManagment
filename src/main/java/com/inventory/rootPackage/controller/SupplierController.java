package com.inventory.rootPackage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PostMapping("/newadd")
	public String addSupplier(@ModelAttribute Wholesaler supplier) {
		service.saveSupplier(supplier);
		return "redirect:/sup/all";
	}
	
	@PostMapping("/newupdate")
	public String updateSupplier(@RequestParam Long id ,@RequestParam String name,
								@RequestParam String contactPerson ,@RequestParam Long phone,
								@RequestParam String email , @RequestParam String address) {
		Wholesaler supplier=service.getSupplier(id).get();
		supplier.setAddress(address);supplier.setContactPerson(contactPerson);supplier.setEmail(email);
		supplier.setId(id);supplier.setName(name);supplier.setPhone(phone);
		service.saveSupplier(supplier);
		return "redirect:/sup/all";
	}
	
	
	@GetMapping("/delete/{id}")
	public String dltSupplier(@PathVariable Long id){
		 service.dltSupplier(id);
		 return "redirect:/sup/all";
	}
	
	@GetMapping("/update/{id}")
	public String updateSupplier(@PathVariable Long id, Model model) {
		Wholesaler sup=service.getSupplier(id).get();
		System.out.println(sup);
		model.addAttribute("supplier",sup);
		return "updateSupplier";
	}
	
	@GetMapping("/all")
	public String allSupplier(Model model) {
		model.addAttribute("suppliers",service.allSupplier());
		return "viewSupplier";
	}
	
	@GetMapping("/addsup")
	public String toJsp() {
		return "addSupplier";
	}
}
