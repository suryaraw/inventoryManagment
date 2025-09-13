package com.inventory.rootPackage.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String razorpayOrderId;    // Razorpay's order id
    private String razorpayPaymentId;  // Razorpay's payment id
    private String razorpaySignature;  // Razorpay's signature
    private Double amount;
    private String status;
}
