package com.inventory.rootPackage.mapper;

import com.inventory.rootPackage.dto.PaymentDTO;
import com.inventory.rootPackage.model.PaymentEntity;


//Helps in converting between DTO and Entity

public class PaymentMapper {
  public static PaymentEntity toEntity(PaymentDTO dto) {
      PaymentEntity entity = new PaymentEntity();
      entity.setOrderId(dto.getRazorpayOrderId());
      entity.setPaymentId(dto.getRazorpayPaymentId());
      entity.setStatus("PENDING"); // default
      entity.setAmount(dto.getAmount());
      
      entity.setCurrency(dto.getCurrency());
      entity.setPaymentMethod(dto.getPaymentMethod());
      entity.setCustomerName(dto.getCustomerName());
      entity.setCustomerEmail(dto.getCustomerEmail());
      entity.setCustomerMob(dto.getCustomerMob());
      entity.setTimestamp(dto.getTimestamp());
      
      return entity;
  }

  public static PaymentDTO toDTO(PaymentEntity entity) {
      PaymentDTO dto = new PaymentDTO();
      dto.setRazorpayOrderId(entity.getOrderId());
      dto.setRazorpayPaymentId(entity.getPaymentId());
      dto.setAmount(entity.getAmount());
      
      dto.setCurrency(entity.getCurrency());
      dto.setPaymentMethod(entity.getPaymentMethod());
      dto.setCustomerName(entity.getCustomerName());
      dto.setCustomerEmail(entity.getCustomerEmail());
      dto.setCustomerEmail(entity.getCustomerMob());
      dto.setTimestamp(entity.getTimestamp());
      return dto;
  }
}