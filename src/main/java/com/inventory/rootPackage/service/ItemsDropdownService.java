package com.inventory.rootPackage.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.dto.ItemDTO;
import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.repository.ItemRepository;

@Service
public class ItemsDropdownService implements ItemService{

	private final ItemRepository itemRepo;

	
	public ItemsDropdownService(ItemRepository itemRepo) {
		super();
		this.itemRepo = itemRepo;
	}
	
	private ItemDTO convertToDTO(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getCategory(),
                item.getBrand(),
                item.getModel(),
                item.getWholesalePrice(),
                item.getRetailPrice(),
                item.getGstRate(),
                item.getDateOfPurchase(),
                item.getSuppliers()
                
        );
    }
	
	
	private Item convertToEntity(ItemDTO dto) {
        return new Item(
                dto.getId(),
                dto.getName(),
                dto.getCategory(),
                dto.getBrand(),
                dto.getModel(),
                dto.getWholesalePrice(),
                dto.getRetailPrice(),
                dto.getGstRate(),
                dto.getDateOfPurchase(),
                dto.getSuppliers()
        );
    }
	

	/*
	 * @Override public ItemDTO saveItem(ItemDTO itemDTO) { Item saved =
	 * itemRepo.save(convertToEntity(itemDTO)); return convertToDTO(saved); }
	 */
	@Override
	public ItemDTO saveItem(ItemDTO itemDTO) {
	    Item item = new Item();
	    item.setName(itemDTO.getName());
	    item.setCategory(itemDTO.getCategory());
	    item.setBrand(itemDTO.getBrand());
	    item.setModel(itemDTO.getModel());
	    item.setWholesalePrice(itemDTO.getWholesalePrice());
	    item.setRetailPrice(itemDTO.getRetailPrice());
	    item.setGstRate(itemDTO.getGstRate());

	    if (itemDTO.getDateOfPurchase() == null) {
	        item.setDateOfPurchase(LocalDate.now());
	    } else {
	        item.setDateOfPurchase(itemDTO.getDateOfPurchase());
	    }

	    item.setSuppliers(itemDTO.getSuppliers());

	    Item saved = itemRepo.save(item);
	    return convertToDTO(saved);
	}


	@Override
	public List<ItemDTO> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
