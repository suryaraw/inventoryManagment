package com.inventory.rootPackage.rag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.rootPackage.Pinecone.PineconeRestClient;

import dev.langchain4j.model.ollama.OllamaEmbeddingModel;

@Service
public class RetriverService {
	
	@Autowired
    private OllamaEmbeddingModel embeddingModel;

    @Autowired
    private PineconeRestClient pineconeClient;

    private final ObjectMapper mapper = new ObjectMapper();

    public List<RetrieverPineconeDto> retrieve(String query) throws IOException {

        // Step-1: Convert query → vector
//        float[] embeddin=g = embeddingModel.embed(query).vector();
        var responseEmbed = embeddingModel.embed(query);
        float[] embedding = responseEmbed.content().vector();

        System.out.println("Generated Query Embedding Length: " + embedding.length);

        // Step-2: Build JSON for Pinecone query
        String json = buildQueryJson(embedding);

        // Step-3: Call Pinecone
        String response = pineconeClient.query(json);
        System.out.println("Pinecone Query Response: " + response);

        // Step-4: Parse results
        return parseResults(response);
    }


    // Convert embedding array → JSON format
    private String buildQueryJson(float[] vector) {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("\"topK\": 5,");
        sb.append("\"includeMetadata\": true,");
        sb.append("\"vector\": [");

        for (int i = 0; i < vector.length; i++) {
            sb.append(vector[i]);
            if (i < vector.length - 1) sb.append(",");
        }

        sb.append("]");
        sb.append("}");
        return sb.toString();
    }


    // Parse Pinecone Query Results
    // main picture 
    private List<RetrieverPineconeDto> parseResults(String jsonResponse) throws IOException {

        JsonNode root = mapper.readTree(jsonResponse);

        List<RetrieverPineconeDto> results = new ArrayList<>();

        JsonNode matches = root.path("matches");
        if (!matches.isArray()) return results;

        for (JsonNode node : matches) {
        	RetrieverPineconeDto dto = new RetrieverPineconeDto();

            dto.setId(node.path("id").asText());
            dto.setScore(node.path("score").asDouble());
            dto.setMetadata(node.path("metadata").toString()); // raw JSON metadata

            results.add(dto);
        }

        return results;
    }
}
