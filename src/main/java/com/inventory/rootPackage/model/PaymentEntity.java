package com.inventory.rootPackage.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "payments")
public class PaymentEntity {
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


   
}
