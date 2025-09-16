package com.inventory.rootPackage.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.inventory.rootPackage.dto.PaymentDTO;
import com.inventory.rootPackage.service.PaymentService;

@Controller
public class PaymentController {

    private final PaymentService paymentService;
   
    @Value("${razorpay.key.id}")
    private String keyId;  // Inject Key ID here

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/pay")
    public String payPage(Model model) {
    	// Pass Razorpay Key ID to JSP
        model.addAttribute("razorpayKeyId", keyId);
        return "payment";
    }

    @PostMapping("/createOrder")
    @ResponseBody
    //used when you want to return JSON or plain text from a controller without this it will search for jsp
    //instead of @Controller if we use @RestController we dont need @ResponseBody
    public String createOrder(@RequestParam Double amount) throws Exception {
    	System.out.println("controller-createorder");
    	System.out.println(paymentService.createOrder(amount));
        return paymentService.createOrder(amount);
    }
    
    @GetMapping("/paymentSuccess")
    public String showPaymentSuccessPage() {
        return "paymentSuccess";
    }

    @PostMapping("/paymentSuccess")
    public String paymentSuccess(@ModelAttribute PaymentDTO paymentDTO, Model model) {
        paymentDTO.setStatus("SUCCESS");
        PaymentDTO savedPayment = paymentService.savePayment(paymentDTO);
        model.addAttribute("msg", "Payment Successful! Transaction Id: " + savedPayment.getRazorpayPaymentId());
        return "paymentSuccess"; // JSP page
    }
    
    
}