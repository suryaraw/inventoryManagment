package com.inventory.rootPackage.service;

import com.inventory.rootPackage.model.PaymentEntity;
import com.inventory.rootPackage.model.PaymentResponseEntity;
import com.inventory.rootPackage.dto.PaymentDTO;
import com.inventory.rootPackage.mapper.PaymentMapper;
import com.inventory.rootPackage.repository.PaymentRepository;
import com.inventory.rootPackage.repository.PaymentResponseRepository;
import com.razorpay.*;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
	
	 @Value("${razorpay.key.id}")
	private String KEY_ID;      
	 @Value("${razorpay.key.secret}")
	 private String KEY_SECRET;
   
	 @Autowired
	 private PaymentRepository repo;
	 
	 @Autowired
	 private PaymentResponseRepository paymentResponseRepo;
	 
	 
    // create order
    public String createOrder(Double amount) throws Exception {
        RazorpayClient client = new RazorpayClient(KEY_ID, KEY_SECRET);

        if (amount <= 0 || amount > 100000) { 
            throw new IllegalArgumentException("Amount must be between ₹1 and ₹1,00,000");
        }

        
        JSONObject orderRequest = new JSONObject();
        
        orderRequest.put("amount", (long)(amount * 100)); // Razorpay = paise
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_" + System.currentTimeMillis());

        Order order = client.orders.create(orderRequest);
        System.out.println(order.toString());
        return order.toString(); // returns JSON with id, amount etc
    }

    // return keyId for checkout.js
    public String getKeyId() {
        return KEY_ID;
    }

    // verify signature
    public boolean verifyPayment(String orderId, String paymentId, String signature) {
        try {
            String data = orderId + "|" + paymentId;
            return Utils.verifySignature(data, signature, KEY_SECRET);
        } catch (Exception e) {
            return false;
        }
    }

    // Save to DB
    public void savePayment(PaymentDTO dto, String status) {
        PaymentEntity entity = PaymentMapper.toEntity(dto);
        entity.setStatus(status);
        repo.save(entity);
    }

    //fetch payment details from razorpay
    public JSONObject fetchPaymentDetails(String paymentId) throws Exception {
        RazorpayClient client = new RazorpayClient(KEY_ID, KEY_SECRET);
        Payment payment = client.payments.fetch(paymentId);
        return payment.toJson(); // returns full JSON
    }
    
    //save response
    public void savePaymentResponse(String orderId, String paymentId, String responseJson,LocalDateTime timestamp) {
        PaymentResponseEntity response = new PaymentResponseEntity();
        response.setOrderId(orderId);
        response.setPaymentId(paymentId);
        response.setResponseJson(responseJson);
        response.setTimestamp(timestamp);

        paymentResponseRepo.save(response);
    }
}
