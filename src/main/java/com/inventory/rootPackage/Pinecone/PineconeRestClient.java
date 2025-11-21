package com.inventory.rootPackage.Pinecone;

import java.io.IOException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import okhttp3.*;

@Service
public class PineconeRestClient {
	
	private final OkHttpClient client;
    private final String apiKey;
    private final String host;
    
    

    public PineconeRestClient(
            @Value("${pinecone.apiKey}") String apiKey,
            @Value("${pinecone.host}") String host) {

        this.apiKey = apiKey;
        this.host = host;

        this.client = new OkHttpClient.Builder()
                .callTimeout(Duration.ofSeconds(30))
                .connectTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(30))
                .retryOnConnectionFailure(true)
                .build();
    }

    public String upsert(String json) throws IOException {
        RequestBody body = RequestBody.create(
                json, MediaType.get("application/json")
        );

        Request request = new Request.Builder()
                .url(host + "/vectors/upsert")
                .addHeader("Api-Key", apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String query(String json) throws IOException {
        RequestBody body = RequestBody.create(
                json, MediaType.get("application/json")
        );

        Request request = new Request.Builder()
                .url(host + "/query")
                .addHeader("Api-Key", apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
	
}
