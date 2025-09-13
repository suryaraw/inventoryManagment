package com.inventory.rootPackage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoperView {
	
	private Long id;
	private String name;
	private String category;
	private String brand;
	private String model;
	private Double price;
	private Double gst;
	private Integer quantity;

}
