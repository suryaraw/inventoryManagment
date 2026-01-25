package com.inventory.rootPackage.service;

import java.time.LocalDate;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.dto.ItemDTO;
import com.inventory.rootPackage.mapper.ItemMapper;
import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.repository.ItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j// from lombok 
public class ItemsDropdownService {

	private final ItemRepository itemRepo;

	
	public ItemsDropdownService(ItemRepository itemRepo) {
		super();
		this.itemRepo = itemRepo;
	}
	
	/*
	 * private ItemDTO convertToDTO(Item item) { return new ItemDTO( item.getId(),
	 * item.getName(), item.getCategory(), item.getBrand(), item.getModel(),
	 * item.getWholesalePrice(), item.getRetailPrice(), item.getGstRate(),
	 * item.getDateOfPurchase(), item.getSuppliers()
	 * 
	 * ); }
	 * 
	 * 
	 * private Item convertToEntity(ItemDTO dto) { return new Item( dto.getId(),
	 * dto.getName(), dto.getCategory(), dto.getBrand(), dto.getModel(),
	 * dto.getWholesalePrice(), dto.getRetailPrice(), dto.getGstRate(),
	 * dto.getDateOfPurchase(), dto.getSuppliers() ); }
	 */
	

	/*
	 * @Override public ItemDTO saveItem(ItemDTO itemDTO) { Item saved =
	 * itemRepo.save(convertToEntity(itemDTO)); return convertToDTO(saved); }
	 */
	@Cacheable(value = "item" , key = "#item.id")
	public ItemDTO saveItem(ItemDTO itemDTO) {
		log.info("item saved to DB");
        Item item = ItemMapper.toEntity(itemDTO);

        // Business rule: default purchase date = today
        if (item.getDateOfPurchase() == null) {
            item.setDateOfPurchase(LocalDate.now());
        }

        Item saved = itemRepo.save(item);
        
        return ItemMapper.toDTO(saved);
    }


	
	
	
}
