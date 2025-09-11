package com.inventory.rootPackage.service;

import java.util.List;

import com.inventory.rootPackage.dto.ItemDTO;



public interface ItemService {
	
	ItemDTO saveItem(ItemDTO item);
	List<ItemDTO> getAllItems();
	

}
