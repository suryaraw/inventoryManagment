package com.inventory.rootPackage.service;

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
	
	/*
	 * private ItemDTO convertToDTO(Item item) { return new ItemDTO( item.getId(),
	 * item.getName(), item.getCategory(), item.getBrand(), item.getModel(),
	 * item.getWholesalePrice(), item.getRetailPrice(), item.getGstRate() ); }
	 */

	@Override
	public ItemDTO saveItem(ItemDTO item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemDTO> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
