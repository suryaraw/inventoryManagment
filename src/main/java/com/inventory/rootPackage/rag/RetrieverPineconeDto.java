package com.inventory.rootPackage.rag;


import lombok.Data;

@Data
public class RetrieverPineconeDto {
	
	private String id;
    private double score;
    private String metadata;

}
