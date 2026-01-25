package com.inventory.rootPackage.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {
	
    private Long itemId;
    private String itemName;
    private String category;

    private Integer stock;
    private Integer needed;
    
}
