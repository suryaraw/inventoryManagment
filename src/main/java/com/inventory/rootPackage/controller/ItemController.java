package com.inventory.rootPackage.controller;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.rootPackage.dto.ItemDTO;
import com.inventory.rootPackage.mapper.ItemMapper;
import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.model.Wholesaler;
import com.inventory.rootPackage.service.ItemAddService;
import com.inventory.rootPackage.service.ItemService;
import com.inventory.rootPackage.service.SupplierService;



@Controller
public class ItemController {
	
	 
	 private final SupplierService supplierservice;
	 
	 private final ItemAddService itemaddservice;

	 public ItemController( SupplierService supplierservice, ItemAddService itemaddservice) {
	       
			this.supplierservice = supplierservice;
			this.itemaddservice = itemaddservice;
	 }

//	@GetMapping("/items/add")
//	public String showAddItemForm(Model model) {
//	    model.addAttribute("item", new Item());
//	    return "addItem";
//	}

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
	public String saveItem(@RequestParam String name,
            @RequestParam String category,
            @RequestParam String brand,
            @RequestParam String modelName,
            @RequestParam Long supplierId,
            @RequestParam Double wholesalePrice,
            @RequestParam Integer quantity,
            @RequestParam Double retailPrice,
            @RequestParam Double gstRate) {
	    // Convert Entity → DTO
		Wholesaler supplier=supplierservice.getSupplier(supplierId).get();
		Item item = new Item();
		item.setBrand(brand);item.setCategory(category);item.setDateOfPurchase(LocalDate.now());
		item.setGstRate(gstRate);item.setName(name);item.setModel(modelName);item.setRetailPrice(retailPrice);
		item.setWholesalePrice(wholesalePrice);item.setSupplier(supplier);item.setQuantity(quantity);
//		System.out.println(" item  ***"+item);
		itemaddservice.saveItem(item);
		
//	    ItemDTO dto = ItemMapper.toDTO(item);
//	    itemService.saveItem(dto);
	    return "redirect:/sup/add";
	}
	 
	 @GetMapping("/items")
	 public String listItems(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer data ,Model model) {
		 
//	     model.addAttribute("items", itemaddservice.itemList(page,data));
	     Page<Item> itemPage = itemaddservice.itemList(page, data);

	     model.addAttribute("items", itemPage.getContent());   // list of 10 items
	     model.addAttribute("currentPage", page);
	     model.addAttribute("totalPages", itemPage.getTotalPages());
	     return "itemsList";
	 }
	 
	 @GetMapping("/items/edit/{id}")
	 public String updateItem(@PathVariable Long id,Model model) {
		 model.addAttribute("item", itemaddservice.getbyId(id));
		 model.addAttribute("suppliers",supplierservice.supplier());
		return "updateItem";
	 }
	 
	 @PostMapping("item/update")
	 public String updatedItem(@ModelAttribute Item item,@RequestParam("supplierId") Long supid ) {
		 item.setSupplier( supplierservice.getSupplier(supid).get());
		 itemaddservice.saveItem(item);
		 return "redirect:/items";
	 }
	 
	 @GetMapping("item/delete/{id}")
	 public String deleteItem(@PathVariable Long id) {
		 itemaddservice.deleteItem(id);
		 return "redirect:/items";
	 }
	 
}
