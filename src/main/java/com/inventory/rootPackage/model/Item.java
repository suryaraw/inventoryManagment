package com.inventory.rootPackage.model;

import java.time.LocalDate;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> origin/feature_surya

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
//	private String category;
	@ManyToOne
    @JoinColumn(name = "category_id") 
    private Category category;
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
    private List<Wholesaler> suppliers;
	
//    private LocalDate dateOfPurchase;
//    @ManyToMany
//    @JoinTable
//    private Wholesaler supplier;
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
//   // @SequenceGenerator(name = "item_seq", sequenceName = "item_sequence", initialValue = 1001, allocationSize = 1)
//    private Long itemId;
//
//    private String itemName;
//    private String category;
//    private String description;
//    private Integer quantityInStock;
////    private String unitOfMeasure;
////    private String location;
//    @ManyToMany
//    @JoinTable
//    private Wholesaler supplier;
//    private Double purchasePrice;
//    private Double sellingPrice;
//    private LocalDate dateOfPurchase;
////    private Integer reorderLevel;
////    private Integer reorderQuantity;
	
}
