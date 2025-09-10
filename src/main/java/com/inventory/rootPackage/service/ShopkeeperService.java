package com.inventory.rootPackage.service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.rootPackage.dto.ShopItem;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ShopkeeperService {
	
	static LinkedList<ShopItem> shopitem = new LinkedList<ShopItem>();
	
	public static List<ShopItem> getItemsForshop() {
		shopitem.addAll(Arrays.asList((new ShopItem(001l,"LED tv 32 INCH ","Television","Samsung","UA32T4010",15000.00,18.0)),
				(new ShopItem(102l,"Refrigerator 250L ","Home Appliance","LG","GL-B252",22000.00,12.0)) ,
				(new ShopItem(103l,"Laptop 15.6 ","Computers","Dell","inspiron 3511",45000.00,18.0)),
				(new ShopItem(104l,"SmartWatch ","Wearable","Noise","colorFitpro",35000.00,18.0)),
	            (new ShopItem(105l, "Washing Machine", "Home Appliance", "Whirlpool", "WM-Royal 6kg", 17000.0, 12.0)),
	            (new ShopItem(106l, "Bluetooth Speaker", "Audio", "JBL", "Flip 6", 8500.0, 18.0)),
	            (new ShopItem(107l, "Air Conditioner", "Cooling", "Voltas", "1.5T-185V", 33000.0, 28.0)),
	            (new ShopItem(108l, "Microwave Oven", "Kitchen", "IFB", "20SC2", 6000.0, 12.0)),
	            (new ShopItem(109l, "Tablet", "Mobile", "Apple", "iPad 9th Gen", 25000.0, 18.0))));
		return shopitem;
	}
	
	
	
	  /* Spring will auto-inject the request. If you accidentally used a wrong annotation
    (@RequestBody or @ModelAttribute instead of plain HttpServletRequest),
    request will be null.
    
    HttpServletRequest request → full request (all params, headers, etc.).
	@RequestParam("name") → one specific parameter from the request.
	@RequestParam List<Long> ids → multiple values for the same parameter name (checkbox list)
    */
//	Map<String, String[]> map= request.getParameterMap();
	public ModelAndView picked(HttpServletRequest request,ModelAndView model) {
		Map<ShopItem,String> picked = new LinkedHashMap<ShopItem,String>();
		String[] value = request.getParameterValues("picked");
		Double total =0.0;
		for(int i=0;i<value.length;i++) {
			Long id=Long.parseLong(value[i]);
			Integer quantity=Integer.parseInt(request.getParameter("quantity_"+value[i]));
			for(ShopItem si:shopitem) {
				if(si.getId()==id) {
					Double rate = si.getPrice();
					Double gst = si.getGst();
					Double overall=(rate*quantity)+(((rate*quantity)*gst)/100);
					total += overall;
					String sum=rate+" x "+quantity+" x "+gst+"% = "+overall;
					picked.put(si, sum);
				}
			}
		}
		model.addObject("selectedItem",picked);
		model.addObject("orderSum",total);
		model.setViewName("picked");
		return model;
	}
}
