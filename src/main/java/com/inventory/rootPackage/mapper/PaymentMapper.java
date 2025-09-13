package com.inventory.rootPackage.mapper;

import com.inventory.rootPackage.dto.PaymentDTO;
import com.inventory.rootPackage.model.PaymentEntity;


public class PaymentMapper {

    public static PaymentDTO toDTO(PaymentEntity entity) {
        if (entity == null) return null;

        return PaymentDTO.builder()
                .id(entity.getId())
                .razorpayOrderId(entity.getRazorpayOrderId())
                .razorpayPaymentId(entity.getRazorpayPaymentId())
                .razorpaySignature(entity.getRazorpaySignature())
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .build();
    }

    public static PaymentEntity toEntity(PaymentDTO dto) {
        if (dto == null) return null;

        PaymentEntity payment = new PaymentEntity();
        payment.setId(dto.getId());
        payment.setRazorpayOrderId(dto.getRazorpayOrderId());
        payment.setRazorpayPaymentId(dto.getRazorpayPaymentId());
        payment.setRazorpaySignature(dto.getRazorpaySignature());
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.getStatus());
        return payment;
    }
}