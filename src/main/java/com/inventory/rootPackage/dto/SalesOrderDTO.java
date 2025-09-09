package com.inventory.rootPackage.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDTO {
    private Long id;
    private String invoiceNumber;
    private String orderDate;

    private String customerName;
    private String customerPhone;

    private String shopName;
    private String itemName;

    private Integer quantity;
    private Double unitPrice;
    private Double gstRate;
    private Double gstAmount;
    private Double totalAmount;
}
