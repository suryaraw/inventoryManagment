package com.inventory.rootPackage.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_id")
    private Long id;
    private Long item_id;
    private String name;
    private String category;
    private Integer currentstock;
    private Integer Stockrequested;
    private Double price;
    private Double total;
    private Double overall;
    private LocalDateTime orderDate;
    private String status = "REQUESTED";  // Pending, Completed
    private String suplier;

//    @ManyToOne
//    @JoinColumn(name = "retailer_id")
//    private Retailer retailer;

    @ManyToOne
    @JoinColumn(name = "wholesaler_id")
    private Wholesaler wholesaler;
//
//    @ManyToOne
//    @JoinColumn(name = "item_id")
//    private Item item;
//
//    private Integer quantity;
//    //private Double totalAmount;
//    private Double unitPrice;
//
//    private Double gstRate;     // from Item or override
  //  private Double gstAmount;   // calculated = unitPrice * quantity * gstRate/100
  //  private Double totalAmount; // final = (unitPrice * quantity) + gstAmount
    
    
//    @Transient
//    public Double getGstAmount() {
//        Double rate = gstRate != null ? gstRate : (item != null ? item.getGstRate() : 0);
//        return unitPrice * quantity * rate / 100;
//    }
//
//    @Transient
//    public Double getTotalAmount() {
//        return (unitPrice * quantity) + getGstAmount();
//    }
}

