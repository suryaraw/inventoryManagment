package com.inventory.rootPackage.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payment_responses")
@Data
public class PaymentResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentId; 
    private String orderId;    

    @Lob
    private String responseJson;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}