package com.inventory.rootPackage.dto;

import java.util.Locale.Category;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
	
    private Long id;
    private String name;
    private Category category;
    private String brand;
    private String model;
    private Double wholesalePrice;
    private Double retailPrice;
    private Double gstRate;
}

