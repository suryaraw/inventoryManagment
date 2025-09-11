package com.inventory.rootPackage.dto;


import java.time.LocalDate;
import java.util.List;

import com.inventory.rootPackage.model.Wholesaler;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
	
    private Long id;
    private String name;
    private String category;
    private String brand;
    private String model;
    private Double wholesalePrice;
    private Double retailPrice;
    private Double gstRate;
    private LocalDate dateOfPurchase;
    private List<Wholesaler> suppliers; 
}

