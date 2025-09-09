package com.inventory.rootPackage.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailShopDTO {
    private Long id;
    private String shopName;
    private String location;
    private String phone;
    private String retailerName; // from Retailer
}
