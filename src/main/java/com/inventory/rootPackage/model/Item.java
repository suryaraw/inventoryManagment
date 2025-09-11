package com.inventory.rootPackage.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	private String category;
	private String brand;
	private String model;
	private Double wholesalePrice;
	private Double RetailPrice;
	private Double gstRate;
	private LocalDate dateOfPurchase;
	@ManyToMany
    @JoinTable(
        name = "item_wholesaler", // name of join table
        joinColumns = @JoinColumn(name = "item_id"), // FK to Item
        inverseJoinColumns = @JoinColumn(name = "wholesaler_id") // FK to Wholesaler
    )
	/* @ManyToOne */
    private List<Wholesaler> suppliers;
	

}
