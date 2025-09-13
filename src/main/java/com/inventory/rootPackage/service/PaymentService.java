package com.inventory.rootPackage.service;

import com.inventory.rootPackage.model.PaymentEntity;
import com.inventory.rootPackage.dto.PaymentDTO;
import com.inventory.rootPackage.mapper.PaymentMapper;
import com.inventory.rootPackage.repository.PaymentRepository;
import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Create Razorpay Order
    public String createOrder(Double amount) throws RazorpayException {
        RazorpayClient client = new RazorpayClient(keyId, keySecret);
        System.out.println("---order init---");
        JSONObject options = new JSONObject();
        options.put("amount", amount * 100); // amount in paise
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());

        Order order = client.orders.create(options);
        System.out.println(options);
        System.out.println(order.toString());
        //order.toString(); entire json in testing we can send entire order
        //return order.get("id");//in production only id order.get("id");
        return order.toJson().toString();
    }

    // Save successful payment
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
    	System.out.println("---save---");
    	PaymentEntity entity = PaymentMapper.toEntity(paymentDTO);
    	System.out.println(entity);
    	PaymentEntity saved = paymentRepository.save(entity);
    	System.out.println(saved);
        return PaymentMapper.toDTO(saved);
    }
}
