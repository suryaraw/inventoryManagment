package com.inventory.rootPackage.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inventory.rootPackage.dto.ShopItem;

@Service
public class ShopkeeperService {
	
	 LinkedList<ShopItem> shopitem = new LinkedList<ShopItem>();
	
	protected List<ShopItem> getItemsForshop() {
		shopitem.addAll(Arrays.asList((new ShopItem(001l,"LED tv 32 INCH ","Television","Samsung","UA32T4010",15000.00,18.0)),
				(new ShopItem(002l,"Refrigerator 250L ","Home Appliance","LG","GL-B252",22000.00,12.0)) ,
				(new ShopItem(003l,"Laptop 15.6 ","Computers","Dell","inspiron 3511",45000.00,18.0)),
				(new ShopItem(004l,"SmartWatch ","Wearable","Noise","colorFitpro",35000.00,18.0))));
		return shopitem;
	}
}
