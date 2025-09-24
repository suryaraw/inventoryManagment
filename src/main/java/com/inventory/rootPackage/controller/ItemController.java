package com.inventory.rootPackage.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.model.ShoperPaid;
import com.inventory.rootPackage.model.Wholesaler;
import com.inventory.rootPackage.service.ItemAddService;
import com.inventory.rootPackage.service.OrderService;
import com.inventory.rootPackage.service.SupplierService;

@Controller
public class ItemController {

    private final OrderController orderController;

	private final SupplierService supplierservice;

	private final ItemAddService itemaddservice;
	
	private final	OrderService orderservice;

	public ItemController(SupplierService supplierservice, ItemAddService itemaddservice, OrderService orderservice, OrderController orderController) {

		this.supplierservice = supplierservice;
		this.itemaddservice = itemaddservice;
		this.orderservice = orderservice;
		this.orderController = orderController;
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
	public String saveItem(@RequestParam String name, @RequestParam String category, @RequestParam String brand,
			@RequestParam String modelName, @RequestParam Long supplierId, @RequestParam Double wholesalePrice,
			@RequestParam Integer quantity, @RequestParam Double retailPrice, @RequestParam Double gstRate) {
		// Convert Entity → DTO
		Wholesaler supplier = supplierservice.getSupplier(supplierId).get();
		Item item = new Item();
		item.setBrand(brand);
		item.setCategory(category);
		item.setDateOfPurchase(LocalDate.now());
		item.setGstRate(gstRate);
		item.setName(name);
		item.setModel(modelName);
		item.setRetailPrice(retailPrice);
		item.setWholesalePrice(wholesalePrice);
		item.setSupplier(supplier);
		item.setQuantity(quantity);
//		System.out.println(" item  ***"+item);
		itemaddservice.saveItem(item);

//	    ItemDTO dto = ItemMapper.toDTO(item);
//	    itemService.saveItem(dto);
		return "redirect:/items";
	}

	@GetMapping("/items")
	public String listItems(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer data, Model model) {

//	     model.addAttribute("items", itemaddservice.itemList(page,data));
		Page<Item> itemPage = itemaddservice.itemList(page, data);

		model.addAttribute("items", itemPage.getContent()); // list of 10 items
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", itemPage.getTotalPages());
		return "itemsList";
	}

	@GetMapping("/items/edit/{id}")
	public String updateItem(@PathVariable Long id, Model model) {
		model.addAttribute("item", itemaddservice.getbyId(id));
		model.addAttribute("suppliers", supplierservice.supplier());
		return "updateItem";
	}

	@PostMapping("item/update")
	public String updatedItem(@ModelAttribute Item item, @RequestParam("supplierId") Long supid) {
		item.setSupplier(supplierservice.getSupplier(supid).get());
		itemaddservice.saveItem(item);
		return "redirect:/items";
	}

	@GetMapping("item/delete/{id}")
	public String deleteItem(@PathVariable Long id) {
		itemaddservice.deleteItem(id);
		return "redirect:/items";
	}
	
	@PostMapping("approve/{id}/{quantity}/{paymentId}")
	@ResponseBody
	public Map<String, Object> approve(@PathVariable Long id, @PathVariable Integer quantity, @PathVariable Long paymentId) {
		System.out.println("method called");
	    Map<String, Object> response = new HashMap<>();
	    String result = itemaddservice.modifyQuantity(id, quantity ,paymentId);
	    System.out.println(result);
	    if ("success".equals(result)) {
	        response.put("success", true);
	    } else {
	        response.put("success", false);
	    }

	    return response;
	}
	
	@GetMapping("/sendFailureMail/{id}")
	public String sendFailureMail(@PathVariable Integer id ) {
		System.out.println(id);
		ShoperPaid sp=orderservice.getById(id);
		sp.setDispatchStatus("Insufficient");
		orderservice.saveOrder(sp);
		System.out.println("acsddfv");
	    return "redirect:/orders/order";  // or a JSP page
	}


}
