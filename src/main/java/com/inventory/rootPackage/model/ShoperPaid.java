package com.inventory.rootPackage.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "OrderedItem")
public class ShoperPaid {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer s_no;
	private Long item_id;
	private String name;
	private String category;
	private String brand;
	private String model;
	private Double price;
	private Double gst;
	private Integer quantity;
	private Double totalprice;
	private Double amountPaid;
	private Double overall;
	
	@ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
	private PaymentEntity paymentId;
	

}
