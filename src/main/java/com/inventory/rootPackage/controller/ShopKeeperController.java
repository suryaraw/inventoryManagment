package com.inventory.rootPackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.rootPackage.dto.ItemDTO;
import com.inventory.rootPackage.service.ShopkeeperService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop")
public class ShopKeeperController {


//  ----------------- Manish -----------------
	 // Show retail shop dashboard
	@Autowired
	private ShopkeeperService service;

    @GetMapping("/items")
    public String retaildashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        
        
        List<ItemDTO> itemsForshop = service.getAllItems();
        model.addAttribute("items",itemsForshop);
        return "retailDashboard";
    }

  
    @PostMapping("/selected")
    public ModelAndView selected(HttpServletRequest request ,ModelAndView model) {
    		if(request==null) {model.setViewName("retailDashboard");return model;}
    		return service.picked(request, model);
    }
    
}
