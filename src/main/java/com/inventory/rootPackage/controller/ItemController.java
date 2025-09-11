package com.inventory.rootPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.inventory.rootPackage.dto.ItemDTO;
import com.inventory.rootPackage.mapper.ItemMapper;
import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.service.ItemService;



@Controller
public class ItemController {
	
	 private final ItemService itemService;

	    public ItemController(ItemService itemService) {
	        this.itemService = itemService;
	    }

	@GetMapping("/items/add")
	public String showAddItemForm(Model model) {
	    model.addAttribute("item", new Item());
	    return "addItem";
	}

	/*
	 * @PostMapping("/items/save") public String saveItem(@ModelAttribute("item")
	 * Item item) { // Convert Item → ItemDTO ItemDTO dto = new ItemDTO(
	 * item.getId(), item.getName(), item.getCategory(), item.getBrand(),
	 * item.getModel(), item.getWholesalePrice(), item.getRetailPrice(),
	 * item.getGstRate(), item.getDateOfPurchase(), item.getSuppliers() );
	 * 
	 * itemService.saveItem(dto); return "redirect:/dashboard"; }
	 */
	@PostMapping("/items/save")
	public String saveItem(@ModelAttribute("item") Item item) {
	    // Convert Entity → DTO
	    ItemDTO dto = ItemMapper.toDTO(item);
	    itemService.saveItem(dto);
	    return "redirect:/dashboard";
	}
	 
	 @GetMapping("/items")
	 public String listItems(Model model) {
	     model.addAttribute("items", itemService.getAllItems());
	     return "itemsList";
	 }

	
}
