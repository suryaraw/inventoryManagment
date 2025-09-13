package com.inventory.rootPackage.mapper;

import com.inventory.rootPackage.dto.ItemDTO;
import com.inventory.rootPackage.model.Item;

public class ItemMapper {

	public static ItemDTO toDTO(Item item) {
        if (item == null) return null;
        
//        Long id;
//        private String name;
//        private String category;
//        private String brand;
//        private String model;
//        private Double retailPrice;
//        private Double gstRate;
//        private Integer quantity;
//    }

        return new ItemDTO(
            item.getId(),
            item.getName(),
            item.getCategory(),
            item.getBrand(),
            item.getModel(),
            item.getRetailPrice(),
            item.getGstRate(),
            null,
            null
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
        item.setRetailPrice(dto.getRetailPrice());
        item.setGstRate(dto.getGstRate());
        return item;
    }
	
}
