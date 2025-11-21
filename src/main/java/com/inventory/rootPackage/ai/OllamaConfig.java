package com.inventory.rootPackage.ai;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import jakarta.annotation.PostConstruct;
import okhttp3.OkHttpClient;

@Configuration
public class OllamaConfig {
	
	@Bean
	public OllamaChatModel ollama() {

	    OkHttpClient httpClient = new OkHttpClient.Builder()
	            .callTimeout(Duration.ofSeconds(300))
	            .connectTimeout(Duration.ofSeconds(60))
	            .readTimeout(Duration.ofSeconds(300))
	            .writeTimeout(Duration.ofSeconds(300))
	            .retryOnConnectionFailure(true)
	            .build();

	    return OllamaChatModel.builder()
	            .baseUrl("http://localhost:11434")
	            .modelName("llama3.2:1b")// updated
	            .temperature(0.4)
//	            .httpClient(httpClient)     // <<< IMPORTANT
	            .build();
	}

	
	@Bean
    public OllamaEmbeddingModel embeddingModel() {
		
		OkHttpClient httpClient = new OkHttpClient.Builder()
				 .callTimeout(Duration.ofSeconds(300))    // 5 minutes
	             .connectTimeout(Duration.ofSeconds(60))
	             .readTimeout(Duration.ofSeconds(300))
	             .writeTimeout(Duration.ofSeconds(300))
                .retryOnConnectionFailure(true)
                .build();

        return OllamaEmbeddingModel.builder()
                .modelName("nomic-embed-text")   // your embedding model
                .baseUrl("http://localhost:11434") // Ollama server
                .build();
    }
	
	@Bean(name = "ollamaHttpClient")
	public OkHttpClient ollamaHttpClient() {
	    return new OkHttpClient.Builder()
	            .callTimeout(Duration.ofSeconds(300))
	            .readTimeout(Duration.ofSeconds(300))
	            .connectTimeout(Duration.ofSeconds(60))
	            .writeTimeout(Duration.ofSeconds(300))
	            .retryOnConnectionFailure(true)
	            .build();
	}

	
	@PostConstruct
	public void setup() {
	    System.setProperty("langchain4j.ollama.http-client-bean", "ollamaHttpClient");
	}


}
