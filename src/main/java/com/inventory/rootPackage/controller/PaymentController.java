package com.inventory.rootPackage.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inventory.rootPackage.dto.ItemDTO;
import com.inventory.rootPackage.dto.PaymentDTO;
import com.inventory.rootPackage.model.PaymentEntity;
import com.inventory.rootPackage.model.ShoperPaid;
import com.inventory.rootPackage.service.OrderService;
import com.inventory.rootPackage.service.PaymentService;
import com.inventory.rootPackage.service.ShopkeeperService;

@Controller
public class PaymentController {

    private final PaymentService paymentService;
    
    private final OrderService orderservice;
    

    public PaymentController(PaymentService paymentService, OrderService orderservice) {
        this.paymentService = paymentService;
		this.orderservice = orderservice;
    }

	/*
	 * @GetMapping({"/", "/index"}) public String index() { return "index"; }
	 */
    
    // 1. show checkout.jsp
    @PostMapping("/checkout")
    public String checkout(org.springframework.ui.Model model,@RequestParam("orderSum") 
    Double total ,@RequestParam("forRazor") Double forRazor) {
        model.addAttribute("razorpayKeyId", paymentService.getKeyId());
        model.addAttribute("forRazor",forRazor);
        model.addAttribute("orderSum",total);
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
                                 @RequestParam Double amount,
                                 @RequestParam Double orderSum) throws Exception {

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
        	orderSent(ShopkeeperService.pickedlist,amount,orderSum,razorpayOrderId);
            return "Payment Verified! OrderId=" + razorpayOrderId;
//        		return null;
        } else {
			/*
			 * paymentService.savePayment(dto, "FAILED"); return
			 * "Payment Verification Failed!";
			 */
        	paymentService.savePayment(dto, "PENDING");
            return "Your payment is being verified. If the amount was deducted, it will be automatically updated in our system within 24 hours.";
        }
    }
    
    //Order sent to retailer 
    private void orderSent(List<ItemDTO> pickedlist, Double amount, Double orderSum, String razorpayOrderId) {
    	PaymentEntity payment = paymentService.getPayment(razorpayOrderId);
    
	    	for(int i=0;i<pickedlist.size();i++) {
	    		ShoperPaid order = new ShoperPaid();
	    		ItemDTO item = pickedlist.get(i);
	    		order.setBrand(item.getBrand());order.setCategory(item.getCategory());order.setGst(item.getGstRate());
	    		order.setItem_id(item.getId());order.setModel(item.getModel());order.setName(item.getName());
	    		order.setQuantity(item.getQuantity());order.setTotalprice(item.getAmount());order.setPrice(item.getRetailPrice());
	    		order.setPaymentId(payment);
	    		if(i==pickedlist.size()-1) {
	    			order.setAmountPaid(amount);order.setOverall(orderSum);
	    		}
	    		orderservice.saveOrder(order);
	    	}
	}

	@GetMapping({"/paymentSuccess"})
    public String Success() {
    	return "paymentSuccess";
    	}
}