package com.inventory.rootPackage.ai.Embeddings;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.langchain4j.model.ollama.OllamaEmbeddingModel;

@Service
public class EmbeddingService {
	
	private final OllamaEmbeddingModel embedModel =
            OllamaEmbeddingModel.builder()
                    .baseUrl("http://localhost:11434")
                    .modelName("nomic-embed-text")
                    .build();

	 public List<Double> generateEmbedding(String text) {

	        float[] floats = embedModel.embed(text).content().vector();
	        List<Double> doubles = new ArrayList<>(floats.length);
	        for (float f : floats) {
	            doubles.add((double) f);
	        }

	        return doubles;
	    }

}
