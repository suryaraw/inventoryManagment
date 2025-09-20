package com.inventory.rootPackage.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {
    private Long id;
    private String orderNumber;
    private String orderDate;
    private String status;

//    private String retailerName;
    private String wholesalerName;
    private String itemName;

    private Integer quantity;
    private Double unitPrice;
    private Double gstRate;
    private Double gstAmount;
    private Double totalAmount;
}
