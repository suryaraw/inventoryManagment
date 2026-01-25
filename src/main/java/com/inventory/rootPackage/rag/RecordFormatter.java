package com.inventory.rootPackage.rag;

import org.springframework.stereotype.Component;

import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.model.PaymentEntity;
import com.inventory.rootPackage.model.PaymentResponseEntity;
import com.inventory.rootPackage.model.ShoperPaid;
import com.inventory.rootPackage.model.Wholesaler;

@Component
public class RecordFormatter {
	
	public String formatItem(Item item) {
        return "Item Details: " +
                "ID: " + item.getId() +
                ", Name: " + item.getName() +
                ", Brand: " + item.getBrand() +
                ", Model: " + item.getModel() +
                ", Category: " + item.getCategory() +
                ", Wholesale Price: " + item.getWholesalePrice() +
                ", Retail Price: " + item.getRetailPrice() +
                ", GST Rate: " + item.getGstRate() +
                ", Quantity: " + item.getQuantity() +
                ", Purchase Date: " + item.getDateOfPurchase() +
                ", Supplier: " + (item.getSupplier() != null ? item.getSupplier().getName() : "N/A");
    }

    // 🟩 WHOLESALER formatting
    public String formatWholesaler(Wholesaler s) {
        return "Supplier Details: " +
                "ID: " + s.getId() +
                ", Name: " + s.getName() +
                ", Contact Person: " + s.getContactPerson() +
                ", Phone: " + s.getPhone() +
                ", Email: " + s.getEmail() +
                ", Address: " + s.getAddress();
    }

    // 🟧 SHOPERPaid formatting (ordered items)
    public String formatShoperPaid(ShoperPaid p) {
        return "Purchased Item Details: " +
                "S.No: " + p.getS_no() +
                ", Item ID: " + p.getItem_id() +
                ", Name: " + p.getName() +
                ", Brand: " + p.getBrand() +
                ", Model: " + p.getModel() +
                ", Category: " + p.getCategory() +
                ", Price: " + p.getPrice() +
                ", GST: " + p.getGst() +
                ", Quantity: " + p.getQuantity() +
                ", Total Price: " + p.getTotalprice() +
                ", Amount Paid: " + p.getAmountPaid() +
                ", Overall Amount: " + p.getOverall() +
                ", Dispatch Status: " + p.getDispatchStatus() +
                ", Belongs to Payment ID: " + (p.getPaymentId() != null ? p.getPaymentId().getId() : "N/A");
    }

    // 🟥 PAYMENT ENTITY formatting
    public String formatPaymentEntity(PaymentEntity p) {
        return "Payment Details: " +
                "Payment Record ID: " + p.getId() +
                ", Order ID: " + p.getOrderId() +
                ", Payment ID: " + p.getPaymentId() +
                ", Status: " + p.getStatus() +
                ", Amount: " + p.getAmount() +
                ", Currency: " + p.getCurrency() +
                ", Customer Name: " + p.getCustomerName() +
                ", Customer Email: " + p.getCustomerEmail() +
                ", Customer Mobile: " + p.getCustomerMob() +
                ", Payment Method: " + p.getPaymentMethod() +
                ", Timestamp: " + p.getTimestamp();
    }

    // 🟪 PAYMENT RESPONSE formatting
    public String formatPaymentResponse(PaymentResponseEntity r) {
        return "Payment Response: " +
                "ID: " + r.getId() +
                ", Payment ID: " + r.getPaymentId() +
                ", Order ID: " + r.getOrderId() +
                ", Timestamp: " + r.getTimestamp() +
                ", Response JSON: " + r.getResponseJson();
    }
	
}
