package com.inventory.rootPackage.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.rootPackage.dto.ItemDTO;
import com.inventory.rootPackage.mapper.ItemMapper;
import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.repository.ItemRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ShopkeeperService {
	
	@Autowired
	private ItemRepository itemrepo;
	
	
	
	
	  /* Spring will auto-inject the request. If you accidentally used a wrong annotation
    (@RequestBody or @ModelAttribute instead of plain HttpServletRequest),
    request will be null.
    
    HttpServletRequest request → full request (all params, headers, etc.).
	@RequestParam("name") → one specific parameter from the request.
	@RequestParam List<Long> ids → multiple values for the same parameter name (checkbox list)
    */
//	Map<String, String[]> map= request.getParameterMap();
	public ModelAndView picked(HttpServletRequest request,ModelAndView model) {
		List<ItemDTO> pickedlist = new ArrayList<ItemDTO>();
		pickedlist.clear();
		String[] value = request.getParameterValues("picked");
		Double total =0.0;
		for(int i=0;i<value.length;i++) {
			Long id=Long.parseLong(value[i]);
			Integer quantity=Integer.parseInt(request.getParameter("quantity_"+value[i]));
			Item item=itemrepo.findById(id).get();
			Double amount = (item.getRetailPrice() * quantity ) +
					((item.getRetailPrice() * quantity *item.getGstRate())/100);
			ItemDTO selected = ItemMapper.toDTO(itemrepo.findById(id).get());
			selected.setQuantity(quantity);
			selected.setAmount(amount);
			pickedlist.add(selected);
			total += amount;
			System.out.println(selected);
		}
		model.addObject("selectedItem",pickedlist);
		model.addObject("orderSum",total);
		model.setViewName("picked");
		return model;
		
	}
	
	public List<ItemDTO> getAllItems(){
		LinkedList<ItemDTO> list =new LinkedList<ItemDTO>();
		for(Item item:itemrepo.findAll()) {
			list.add(ItemMapper.toDTO(item));
		}
		return list;
	}
	
}
