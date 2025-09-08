package com.inventory.rootPackage.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    private Long id;
    private String itemName;
    private Integer quantity;
    private String ownerType;  // Wholesaler, Retailer, Shop
    private String ownerName;  // resolve ownerId to name
}
