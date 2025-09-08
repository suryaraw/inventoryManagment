package com.inventory.rootPackage.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetailIssueOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "retail_issueorder_id")
    private Long id;

    private String issueNumber;
    private String issueDate;
    private String status;  // Issued, Delivered

    @ManyToOne
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private RetailShop shop;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer quantity;
    private Double unitPrice;
    private Double gstRate; // optional override

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

