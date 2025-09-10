package com.inventory.rootPackage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShopItem {
	
	 	private Long id;
	    private String name;
	    private String category;
	    private String brand;
	    private String model;
	    private Double Price;
	    private Double gstRate;
	    
}
