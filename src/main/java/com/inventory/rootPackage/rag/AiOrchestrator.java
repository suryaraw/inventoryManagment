package com.inventory.rootPackage.rag;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.ai.AnswerGenrator;
import com.inventory.rootPackage.ai.Intent;

@Service 
public class AiOrchestrator {
	
	@Autowired
    private IntentClassifier classifier;

    @Autowired
    private RetriverService retriever;

    @Autowired
    private AnswerGenrator answerGen;

    @Autowired
    private DatabaseSyncService syncService;

    public String handle(String query) throws Exception {

        Intent intent = classifier.classify(query);
        System.out.println("Detected Intent = " + intent);

        switch (intent) {

            case RAG_QUERY:
                var results = retriever.retrieve(query);
                return answerGen.generateAnswer(query, results);

            case SYNC_DB:
                syncService.syncAll();
                return "Database synced successfully with Pinecone.";

            case NORMAL_QA:
                return answerGen.generateAnswer(query);

            default:
                return "Sorry, I couldn't understand the request.";
        }
    }
    

}
