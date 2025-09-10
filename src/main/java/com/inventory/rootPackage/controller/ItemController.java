package com.inventory.rootPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventory.rootPackage.model.Item;



@Controller
public class ItemController {

	@GetMapping("/items/add")
	public String showAddItemForm(Model model) {
	    model.addAttribute("item", new Item());
	    return "addItem";
	}

}
