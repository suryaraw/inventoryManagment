package com.inventory.rootPackage.ai;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.model.Item;
import com.inventory.rootPackage.rag.RetrieverPineconeDto;

import dev.langchain4j.model.ollama.OllamaChatModel;

@Service
public class AnswerGenrator {
	
	@Autowired
    private OllamaChatModel chatModel;

    public String generateAnswer(String query, List<RetrieverPineconeDto> contexts) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("You are an Inventory Assistant. ");
        prompt.append("Use the below database information to answer the user's question.\n\n");

        // Add retrieved Pinecone context
        for (RetrieverPineconeDto ctx : contexts) {
            prompt.append("Context: ").append(ctx.getMetadata()).append("\n");
        }

        prompt.append("\nUser Query: ").append(query).append("\n");
        prompt.append("Give a helpful, precise answer based only on the given context.\n");

        return chatModel.generate(prompt.toString());
    }
    
    public String generateAnswer(String query) {
    		return chatModel.generate(query);
    }
    
    public String generateAnswer(String query, List<Item> contexts ,int num) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("You are an Inventory Assistant. ");
        prompt.append("Use the below database information to answer the user's question.\n\n");

        // Add retrieved Pinecone context
        for (Item ctx : contexts) {
            prompt.append("Context: ").append(ctx+"\n");
        }

        prompt.append("\nUser Query: ").append(query).append("\n");
        prompt.append("Give a helpful, precise answer based only on the given context.\n");

        return chatModel.generate(prompt.toString());
    }

}
