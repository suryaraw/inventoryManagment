package com.inventory.rootPackage.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "payments")
public class PaymentEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;
    private String paymentId;
    private String status;
    private Double amount;
    
    private String currency;
    private String customerName;
    private String customerEmail;
    private String customerMob;
    private String paymentMethod;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    
    @OneToMany(mappedBy = "paymentId", cascade = CascadeType.ALL)
    private List<ShoperPaid> orderedItems; 


   
}
