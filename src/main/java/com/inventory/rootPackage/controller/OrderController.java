package com.inventory.rootPackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.inventory.rootPackage.repository.OrderRepo;
import com.inventory.rootPackage.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepo orderRepo;
	
	@Autowired
	private  OrderService service;

    OrderController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
	
	@GetMapping("/order")
	public String getOrders(Model model) {
		model.addAttribute("orders" , service.getByNotYet());
		System.out.println(service.getByNotYet());
		return "ordered";
	}
	
	@GetMapping("/dispatched")
	public String getOrdersDispatch(Model model) {
		model.addAttribute("orders" , service.getByDiapatched());
		System.out.println(service.getByDiapatched());
		return "dispatched";
	}
}
