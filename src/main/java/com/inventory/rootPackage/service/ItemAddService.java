package com.inventory.rootPackage.service;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.model.Wholesaler;
import com.inventory.rootPackage.repository.ItemRepository;
import com.inventory.rootPackage.repository.SupplierRepo;

@Service
public class ItemAddService {
	
	@Autowired
	private ItemRepository itemRepo;
	

	@Autowired
	private  SupplierRepo supRepo;

	public Item saveItem(Item item){
		return itemRepo.save(item);
	}
	

	public void addSampleItems() {
		if(itemRepo.findAll().isEmpty()) {
			
			 Wholesaler prestige = supRepo.findById((long) 1).orElseThrow();
			    Wholesaler samsung  = supRepo.findById((long) 2).orElseThrow();
			    Wholesaler tata     = supRepo.findById((long) 3).orElseThrow();

			    List<Item> items = Arrays.asList(
			        new Item(0, "Induction Stove", "Kitchen Appliance", "Prestige", "P123", 2500.0, 3000.0, 18.0, LocalDate.now(), 1000,prestige),
			        new Item(0, "Mixer Grinder", "Kitchen Appliance", "Prestige", "M456", 1800.0, 2200.0, 18.0, LocalDate.now(), 800,prestige),
			        new Item(0, "Pressure Cooker", "Cookware", "Prestige", "C789", 1200.0, 1500.0, 12.0, LocalDate.now(), 1250,prestige),
			        new Item(0, "Gas Stove", "Kitchen Appliance", "Prestige", "G321", 3500.0, 4200.0, 18.0, LocalDate.now(), 650,prestige),
			        new Item(0, "Water Purifier", "Appliance", "Prestige", "W654", 8000.0, 9500.0, 18.0, LocalDate.now(), 852,prestige),

			        new Item(0, "Smartphone", "Electronics", "Samsung", "S22", 40000.0, 48000.0, 18.0, LocalDate.now(), 134,samsung),
			        new Item(0, "LED TV", "Electronics", "Samsung", "TV50", 30000.0, 36000.0, 18.0, LocalDate.now(), 123,samsung),
			        new Item(0, "Refrigerator", "Appliance", "Samsung", "RF300", 25000.0, 29500.0, 18.0, LocalDate.now(), 231,samsung),
			        new Item(0, "Washing Machine", "Appliance", "Samsung", "WM800", 20000.0, 24000.0, 18.0, LocalDate.now(), 963, samsung),
			        new Item(0, "Microwave Oven", "Appliance", "Samsung", "MW900", 10000.0, 12000.0, 18.0, LocalDate.now(), 452, samsung),

			        new Item(0, "Electric Car Battery", "Automobile", "TATA", "BATT1", 60000.0, 72000.0, 18.0, LocalDate.now(),563,tata),
			        new Item(0, "Truck Tyre", "Automobile", "TATA", "TY123", 15000.0, 18000.0, 12.0, LocalDate.now(), 489,tata),
			        new Item(0, "Steel Rods", "Construction", "TATA", "SR456", 50000.0, 58000.0, 12.0, LocalDate.now(), 521,tata),
			        new Item(0, "Cement Bags", "Construction", "TATA", "CB789", 350.0, 420.0, 5.0, LocalDate.now(), 321, tata),
			        new Item(0, "Solar Panel", "Energy", "TATA", "SP001", 15000.0, 17500.0, 12.0, LocalDate.now(), 500,tata),

			        new Item(0, "Rice Cooker", "Kitchen Appliance", "Prestige", "RC111", 2500.0, 3000.0, 12.0, LocalDate.now(), 522,prestige),
			        new Item(0, "Hand Blender", "Kitchen Appliance", "Prestige", "HB222", 1500.0, 1800.0, 12.0, LocalDate.now(), 653,prestige),
			        new Item(0, "Ceiling Fan", "Electronics", "Samsung", "CF333", 2000.0, 2400.0, 12.0, LocalDate.now(), 635,samsung),
			        new Item(0, "Air Conditioner", "Electronics", "Samsung", "AC444", 35000.0, 42000.0, 18.0, LocalDate.now(), 200,samsung),
			        new Item(0, "Laptop", "Electronics", "Samsung", "L555", 55000.0, 62000.0, 18.0, LocalDate.now(), 300,samsung),

			        new Item(0, "Iron Rod", "Construction", "TATA", "IR666", 45000.0, 52000.0, 12.0, LocalDate.now(), 600,tata),
			        new Item(0, "Electric Bus Motor", "Automobile", "TATA", "EB777", 120000.0, 140000.0, 18.0, LocalDate.now(), 985,tata),
			        new Item(0, "Cooking Pan", "Cookware", "Prestige", "CP888", 1800.0, 2200.0, 12.0, LocalDate.now(), 852,prestige),
			        new Item(0, "Vacuum Cleaner", "Appliance", "Samsung", "VC999", 9000.0, 11000.0, 18.0, LocalDate.now(), 29,samsung),
			        new Item(0, "Steel Pipes", "Construction", "TATA", "SP101", 30000.0, 35000.0, 12.0, LocalDate.now(), 85,tata)
			    );

			    itemRepo.saveAll(items);
		}
	   
	}
	
	public Page<Item> itemList(Integer page ,Integer data){
		Pageable limit = (Pageable) PageRequest.of(page, data);
		return itemRepo.findAll(limit);
	}
	
	public Item getbyId(Long id) {
		return itemRepo.findById(id).get();
	}
	
	public void deleteItem(Long id) {
		itemRepo.deleteById(id);
	}
}
