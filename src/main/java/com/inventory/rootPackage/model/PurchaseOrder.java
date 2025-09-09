package com.inventory.rootPackage.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_id")
    private Long id;

    private String orderNumber;
    private String orderDate;
    private String status;  // Pending, Completed

    @ManyToOne
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;

    @ManyToOne
    @JoinColumn(name = "wholesaler_id")
    private Wholesaler wholesaler;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer quantity;
    //private Double totalAmount;
    private Double unitPrice;

    private Double gstRate;     // from Item or override
  //  private Double gstAmount;   // calculated = unitPrice * quantity * gstRate/100
  //  private Double totalAmount; // final = (unitPrice * quantity) + gstAmount
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

