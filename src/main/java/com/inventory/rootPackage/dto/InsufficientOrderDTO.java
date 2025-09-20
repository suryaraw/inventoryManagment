package com.inventory.rootPackage.dto;

import com.inventory.rootPackage.model.ShoperPaid;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class InsufficientOrderDTO {

    private Integer sNo;
    private Long itemId;
    private String name;
    private String category;
    private Integer quantityOrdered;
    private Integer currentStock;
    private String brand;

    // Constructor to map directly from ShoperPaid + stock
    public InsufficientOrderDTO(ShoperPaid order, Integer stock) {
        this.sNo = order.getS_no();
        this.itemId = order.getItem_id();
        this.name = order.getName();
        this.category = order.getCategory();
        this.quantityOrdered = order.getQuantity();
        this.brand = order.getBrand();
        this.currentStock = stock;
    }
}



