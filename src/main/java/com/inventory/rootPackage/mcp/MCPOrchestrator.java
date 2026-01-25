package com.inventory.rootPackage.mcp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.rootPackage.temporal.PineconeSyncWorkFlow;

import dev.langchain4j.model.ollama.OllamaChatModel;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MCPOrchestrator {
	
		@Autowired
	    private OllamaChatModel llm;

	    @Autowired
	    private ToolRegistry toolRegistry;
	    
	    @Autowired
	    private WorkflowClient workFlowClient;

	    private ObjectMapper mapper = new ObjectMapper();

	    // Load all schemas from resources/mcp/schemas folder
	    private String loadSchemas() {
	        try {
	            ClassLoader loader = getClass().getClassLoader();

	            // Get the folder from classpath
	            var resource = loader.getResource("com/inventory/rootPackage/mcp/schemas");
	            if (resource == null) {
	                return "[]";
	            }

	            Path folder = Path.of(resource.toURI());
	            StringBuilder sb = new StringBuilder();

	            Files.list(folder).forEach(file -> {
	                try {
	                    sb.append(Files.readString(file)).append("\n");
	                } catch (Exception ignored) {}
	            });

	            return sb.toString();

	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return "[]";
	        }
	    }


	    public Object handleUserQuery(String userQuery) {

	        try {
	            String schemaText = loadSchemas();

	            String prompt = """
	                You are an MCP Orchestrator.
	                You have access to tools defined in the following schemas:

	                %s

	                When a user asks something, reply in STRICT JSON:
	                {
	                    "tool": "<tool-name | NONE>",
	                    "params": { ... }
	                }

	                Rules:
	                - If RAG-related -> use "search_rag"
	                - If syncing Pinecone -> use "sync_pinecone"
	                - If asking inventory -> use "get_inventory"
	                - If normal question -> tool = "NONE"
	                
	                USER QUERY: "%s"
	                """.formatted(schemaText, userQuery);

	            String response = llm.generate(prompt);

	            System.out.println("MCP Raw = " + response);

	            JsonNode root = mapper.readTree(response);
	            String tool = root.get("tool").asText();
	            System.out.println("tool - " + tool);
	            log.debug("tool guessed" + tool);	

	            if (tool.equalsIgnoreCase("NONE")) {
	                System.out.println("executed normal query");

	                String answer = llm.generate("Answer normally: " + userQuery);

	                Map<String, Object> resp = new HashMap<>();
	                resp.put("tool", "NONE");
	                resp.put("answer", answer);

	                return resp;   // front-end can read properly
	            }
//
	            JsonNode params = root.get("params");

	            	if (tool.equals("sync_pinecone")) {

	            	    String mode = root.get("params").get("mode").asText();

	            	    PineconeSyncWorkFlow workflow = workFlowClient.newWorkflowStub(
	            	            PineconeSyncWorkFlow.class,
	            	            WorkflowOptions.newBuilder()
	            	                    .setTaskQueue("SYNC-TASK-QUEUE")
	            	                    .build()
	            	    );
	            	    workflow.syncAll(mode);
	            	    Map<String, String> resp = new HashMap<>();
		                resp.put("answer", "Dont worry chello Pinecone sync started via Temporal Engine and its going on");

		                return resp;  
	            	}

	            	       // Start workflow
	            Object paramObj = mapper.convertValue(params, Object.class);
	            System.out.println("executed" + paramObj);
	            Object answer = toolRegistry.execute(tool, paramObj);
//	            String answer = llm.generate(userQuery ,);
	            
	            Map<String, Object> resp = new HashMap<>();
//	            resp.put("tool", tool);
	            resp.put("answer", answer);
	            
	            return resp;

	            
	           

	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return "Error running MCP: " + ex.getMessage();
	        }
	    }
		
	

}
