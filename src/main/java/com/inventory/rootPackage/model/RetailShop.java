package com.inventory.rootPackage.model;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetailShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "retail_shop_id")
    private Long id;

    private String shopName;
    private String location;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;
}

