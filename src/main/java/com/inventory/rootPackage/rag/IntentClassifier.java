package com.inventory.rootPackage.rag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.ai.Intent;

import dev.langchain4j.model.ollama.OllamaChatModel;

@Service
public class IntentClassifier {

	 	@Autowired
	    private OllamaChatModel llm;

	    public Intent classify(String query) {
	    	
	    	String prompt = """
	    			You are an INTENT CLASSIFIER. 

	    			-----------------------------
	    			CLASSIFICATION RULES (VERY DETAILED):
	    			-----------------------------

	    			### 1. RAG_QUERY  
	    			Choose **RAG_QUERY** ONLY IF the user is asking for any information related to:
	    			- Inventory
	    			- Items or products
	    			- Stock count / item availability
	    			- Brand, model, category
	    			- Supplier or wholesaler details
	    			- Warehouse, quantity, price, GST
	    			- Order details, purchasing info
	    			- Anything stored in Pinecone or MySQL product data

	    			Examples of RAG_QUERY:
	    			- "Show Samsung stock"
	    			- "How many laptops available?"
	    			- "Tell me details of item ID 20"
	    			- "List all iPhones in inventory"
	    			- "What is the price of LED TV 50?"
	    			- "Give me my store inventory summary"

	    			If the question requires using Pinecone retrieval → CLASSIFY AS **RAG_QUERY**.

	    			--------------------------------

	    			### 2. NORMAL_QA  
	    			Choose **NORMAL_QA** if the user is asking:
	    			- General knowledge
	    			- General AI questions
	    			- Explanation of a concept
	    			- Code help
	    			- Chatting or casual questions
	    			- Anything unrelated to inventory or pinecone sync

	    			Examples:
	    			- "What is AI?"
	    			- "Explain Spring Boot"
	    			- "How are you?"
	    			- "Tell me a joke"
	    			- "What is OAuth2?"
	    			- "Explain Java Collections"

	    			--------------------------------

	    			### 3. SYNC_DB  
	    			Choose **SYNC_DB** only when the user asks about:
	    			- syncing Pinecone with database
	    			- refreshing pinecone index
	    			- updating embeddings
	    			- removing duplicates
	    			- rebuilding or reloading RAG data
	    			- pushing new data from MySQL to Pinecone

	    			Keywords that trigger SYNC_DB:
	    			"update pinecone", 
	    			"sync pinecone", 
	    			"reload index", 
	    			"refresh embeddings", 
	    			"rebuild vector store",
	    			"push db to pinecone",
	    			"upsert everything again",
	    			"resync",
	    			"update rag database"

	    			Examples:
	    			- "Sync my DB with Pinecone"
	    			- "Update all embeddings"
	    			- "Refresh my inventory index"
	    			- "Rebuild RAG database"

	    			--------------------------------
	    			RESPONSE RULES (MANDATORY):
	    			--------------------------------
	    			- You must return EXACTLY ONE label.
	    			- VALID outputs are ONLY:
	    			  1.RAG_QUERY
	    			  2.NORMAL_QA
	    			  3.SYNC_DB

	    			STRICTLY DO NOT:
	    			- Do NOT explain your reasoning.
	    			- Do NOT add any extra text.
	    			- Do NOT add punctuation.
	    			- Do NOT use quotes.
	    			- Do NOT generate sentences.
	    			- Do NOT output anything except the label.

	    			--------------------------------
	    			USER QUERY:
	    			"%s"

	    			Return ONLY one label among this (RAG_QUERY,NORMAL_QA,SYNC_DB)
	    			
	    			""".formatted(query);


//	        String prompt = """
//	        Classify the user's intent into one of the following categories:
//	        - RAG_QUERY : If the user is asking about items, brands, stock, prices, suppliers, warehouse ,or inventory.
//	        - NORMAL_QA : If it is a general question or explanation.
//	        - SYNC_DB : If user wants to sync, update or refresh Pinecone index.
//	        
//	        User Query: "%s"
//	        
//	        Return ONLY one of single word in double quotes: "RAG_QUERY", "NORMAL_QA", "SYNC_DB"
//	        """.formatted(query);

	        String result = llm.generate(prompt).trim();
	        result = result.replace("\"", "").trim();   // remove quotes

	        System.err.println(result);
	        try {
	            return Intent.valueOf(result);
	        } catch (Exception e) {
	        		System.out.println(e.getMessage());
	            return Intent.UNKNOWN;
	        }
	    }


}
