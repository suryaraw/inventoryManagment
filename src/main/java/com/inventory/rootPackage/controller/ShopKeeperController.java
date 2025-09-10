package com.inventory.rootPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/shop")
public class ShopKeeperController {

//  ----------------- Manish -----------------
  @GetMapping("/items")
  public  String itemsForShop() {
  		
  	return null;
  }
  
}
