package com.inventory.rootPackage.mcp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.inventory.rootPackage.ai.AnswerGenrator;
import com.inventory.rootPackage.rag.DatabaseSyncService;
import com.inventory.rootPackage.rag.RetriverService;
import com.inventory.rootPackage.repository.ItemRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class MCPToolConfig {

	@Autowired
	private ToolRegistry toolregistry;
	
	@Autowired
    private RetriverService retriever;

    @Autowired
    private AnswerGenrator answerGen;

    @Autowired
    private DatabaseSyncService syncService;
    
    @Autowired
    private ItemRepository items;

    
    @PostConstruct
    public void registerTools() {
    		
    	
//    	 toolregistry.registerTool(
//    	            "sync_pinecone",
//    	            params -> {
//    	                syncService.syncAll();
//    	                return "Pinecone sync completed.";
//    	            }
//    	    );
    		
    		toolregistry.registerTool("get_inventory", a->{try {
				return answerGen.generateAnswer(a.toString(), items.findAll() ,2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return a;});
//    		toolregistry.registerTool("sync_pinecone", a->{try {
//				syncService.syncAll();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}return "Synced data to pinecone";});
    		toolregistry.registerTool("search_rag", a->{try {
//				return (Object)retriever.retrieve(a.toString());
    			return answerGen.generateAnswer(a.toString(), retriever.retrieve(a.toString()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return a;});
    	
    }
	

}
