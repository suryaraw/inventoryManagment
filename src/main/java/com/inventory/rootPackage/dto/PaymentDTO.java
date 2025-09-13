package com.inventory.rootPackage.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
	  	private Long id;
	    private String razorpayOrderId;
	    private String razorpayPaymentId;
	    private String razorpaySignature;
	    private Double amount;
	    private String status;
}