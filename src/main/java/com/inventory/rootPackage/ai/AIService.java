package com.inventory.rootPackage.ai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.rag.RetrieverPineconeDto;
import com.inventory.rootPackage.rag.RetriverService;

import dev.langchain4j.model.ollama.OllamaChatModel;

@Service
public class AIService {
	
	@Autowired
	private OllamaChatModel ollama;

//	public Map<String,String> handleQuery(String query){
//		System.out.println(query);
//		String result = ollama.generate(query);
//		System.out.println(result);
//		Map<String, String> resp = new HashMap<>();
//        resp.put("answer", result);
//        return resp;
//	}
	
	@Autowired
    private RetriverService retriever;

    @Autowired
    private AnswerGenrator answerGen;

    public Map<String, String> handleQuery(String query) {

        Map<String, String> resp = new HashMap<>();

        try {
            // 1️⃣ Get Pinecone Context
            List<RetrieverPineconeDto> contexts = retriever.retrieve(query);

            // 2️⃣ Generate Answer using LLM + context
            String answer = answerGen.generateAnswer(query, contexts);

            resp.put("answer", answer);
            return resp;

        } catch (Exception e) {
            e.printStackTrace();
            resp.put("answer", "AI Server Error. Please try again.");
            return resp;
        }
    }
}
