package com.inventory.rootPackage.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
	 	private String razorpayOrderId;
	    private String razorpayPaymentId;
	    private String razorpaySignature;
	    private Double amount;
	    
	    private String currency;
	    private String customerName;
	    private String customerEmail;
	    private String customerMob;
	    private String paymentMethod;
	    private LocalDateTime timestamp; 
}