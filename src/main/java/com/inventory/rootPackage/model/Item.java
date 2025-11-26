package com.inventory.rootPackage.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "supplier")
public class Item implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private long id;
	private String name;
	private String category;
	private String brand;
	private String model;
	private Double wholesalePrice;
	private Double RetailPrice;
	private Double gstRate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")   // must match <input type="date">
	private LocalDate dateOfPurchase;
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	@JsonIgnore
    private Wholesaler supplier;
	
	

}
