package com.inventory.rootPackage.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailerDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String wholesalerName; // from Wholesaler
}

