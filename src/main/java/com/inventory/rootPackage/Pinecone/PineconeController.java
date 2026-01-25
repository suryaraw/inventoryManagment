package com.inventory.rootPackage.Pinecone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pinecone")
public class PineconeController {
	
	private final PineconeIndexer indexer;

    public PineconeController(PineconeIndexer indexer) {
        this.indexer = indexer;
    }

    @GetMapping("/test")
    public String test() throws Exception {

        indexer.indexRecord(
                "item-1001",
                "Item: Samsung S21, Stock: 12, Supplier: Ramesh Electronics"
        );

        return "Inserted!";
    }

}
