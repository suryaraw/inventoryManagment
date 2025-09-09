package com.inventory.rootPackage.model;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private long id;
	private String name;
	//private String category;
	@ManyToOne
    @JoinColumn(name = "category_id") 
    private Category category;
	private String brand;
	private String model;
	private Double wholesalePrice;
	private Double RetailPrice;
	private Double gstRate;
	
}
