package com.inventory.rootPackage.Pinecone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inventory.rootPackage.ai.Embeddings.EmbeddingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PineconeIndexer {
	
//	@Autowired
	private  PineconeRestClient pinecone;
//	@Autowired
    private  EmbeddingService embed;
    
    private final ObjectMapper mapper = new ObjectMapper();

    public PineconeIndexer(PineconeRestClient pinecone, EmbeddingService embed) {
        this.pinecone = pinecone;
        this.embed = embed;
    }

    public void indexRecord(String id, String text) throws Exception {

        // 1. Convert text → embedding vector
        List<Double> vector = embed.generateEmbedding(text);

        // 2. Build the payload
        Map<String, Object> vectorMap = new HashMap<>();
        vectorMap.put("id", id);
        vectorMap.put("values", vector);
        vectorMap.put("metadata", Map.of("text", text));

        Map<String, Object> request = Map.of("vectors", List.of(vectorMap));

        // 3. Convert to JSON
        String jsonPayload = mapper.writeValueAsString(request);

        // 4. Upsert
        String response = pinecone.upsert(jsonPayload);

        System.out.println("UPSERT RESPONSE: " + response);
    }
}
