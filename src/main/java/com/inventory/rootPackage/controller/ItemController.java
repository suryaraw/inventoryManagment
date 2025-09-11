package com.inventory.rootPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.inventory.rootPackage.model.Item;



@Controller
public class ItemController {

	@GetMapping("/items/add")
	public String showAddItemForm(Model model) {
	    model.addAttribute("item", new Item());
	    return "addItem";
	}

	@PostMapping("/items/save")
	public String saveItem(@ModelAttribute("item") Item item) {
	    return "redirect:/items";
	}

	
}
