package com.inventory.rootPackage.mapper;

import com.inventory.rootPackage.dto.ItemDTO;
import com.inventory.rootPackage.model.Item;

public class ItemMapper {

	public static ItemDTO toDTO(Item item) {
        if (item == null) return null;

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

    public static Item toEntity(ItemDTO dto) {
        if (dto == null) return null;

        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setCategory(dto.getCategory());
        item.setBrand(dto.getBrand());
        item.setModel(dto.getModel());
        item.setWholesalePrice(dto.getWholesalePrice());
        item.setRetailPrice(dto.getRetailPrice());
        item.setGstRate(dto.getGstRate());
        item.setDateOfPurchase(dto.getDateOfPurchase());
        item.setSuppliers(dto.getSuppliers());
        return item;
    }
	
}
