package com.inventory.rootPackage.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_order_id")
    private Long id;

    private String invoiceNumber;
    private String orderDate;
    private String customerName;
    private String customerPhone;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private RetailShop shop;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer quantity;
    private Double unitPrice;

    private Double gstRate;     // from Item
   // private Double gstAmount;   // = unitPrice * quantity * gstRate / 100
   // private Double totalAmount; // = (unitPrice * quantity) + gstAmount
    @Transient
    public Double getGstAmount() {
        Double rate = gstRate != null ? gstRate : (item != null ? item.getGstRate() : 0);
        return unitPrice * quantity * rate / 100;
    }

    @Transient
    public Double getTotalAmount() {
        return (unitPrice * quantity) + getGstAmount();
    }
}

