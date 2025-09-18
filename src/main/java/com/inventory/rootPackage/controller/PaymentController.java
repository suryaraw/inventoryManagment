package com.inventory.rootPackage.controller;


import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.inventory.rootPackage.dto.PaymentDTO;
import com.inventory.rootPackage.service.PaymentService;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

	/*
	 * @GetMapping({"/", "/index"}) public String index() { return "index"; }
	 */
    
    // 1. show checkout.jsp
    @GetMapping("/checkout")
    public String checkout(org.springframework.ui.Model model) {
        model.addAttribute("razorpayKeyId", paymentService.getKeyId());
        return "checkout"; // /WEB-INF/jsp/checkout.jsp
    }

    // 2. create order
    @PostMapping(value = "/createOrder", produces = "application/json")
    @ResponseBody
    public String createOrder(@RequestParam Double amount) throws Exception {
        return paymentService.createOrder(amount);
    }

    // 3. verify payment
    @PostMapping("/paymentSuccess")
    @ResponseBody
    public String paymentSuccess(@RequestParam String razorpayOrderId,
                                 @RequestParam String razorpayPaymentId,
                                 @RequestParam String razorpaySignature,
                                 @RequestParam Double amount) throws Exception {

        boolean valid = paymentService.verifyPayment(razorpayOrderId, razorpayPaymentId, razorpaySignature);
        System.out.println("payment-signature-verify:"+valid);
        
        //fetch details from razorpay
        JSONObject paymentJson = paymentService.fetchPaymentDetails(razorpayPaymentId);
        
        

        String method = paymentJson.getString("method");       // card, upi, netbanking, etc.
        String email = paymentJson.optString("email");        // customer email
        String contact = paymentJson.optString("contact");    // phone number
        String name = paymentJson.optString("customer_name"); 
        
        
        PaymentDTO dto = new PaymentDTO();
        dto.setRazorpayOrderId(razorpayOrderId);
        dto.setRazorpayPaymentId(razorpayPaymentId);
        dto.setRazorpaySignature(razorpaySignature);
        dto.setAmount(amount); 
        
        dto.setCurrency("INR");
        dto.setCustomerName(name);
        dto.setCustomerEmail(email);
        dto.setCustomerMob(contact);
        dto.setPaymentMethod(method);
        dto.setTimestamp(LocalDateTime.now()); 

        
        if (valid) {
        	paymentService.savePayment(dto, "SUCCESS");
        	//save raw json response
        paymentService.savePaymentResponse(razorpayOrderId, razorpayPaymentId, paymentJson.toString(),LocalDateTime.now());
        	System.out.println("payment verified!!");
            return "Payment Verified! OrderId=" + razorpayOrderId;
        } else {
			/*
			 * paymentService.savePayment(dto, "FAILED"); return
			 * "Payment Verification Failed!";
			 */
        	paymentService.savePayment(dto, "PENDING");
            return "Your payment is being verified. If the amount was deducted, it will be automatically updated in our system within 24 hours.";
        }
    }
    
    @GetMapping({"/paymentSuccess"})
    public String Success() {
    	return "paymentSuccess";
    	}
}