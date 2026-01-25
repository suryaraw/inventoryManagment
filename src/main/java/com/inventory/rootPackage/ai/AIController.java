package com.inventory.rootPackage.ai;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.rootPackage.mcp.MCPOrchestrator;

@RestController
@RequestMapping("api/ai")
public class AIController {

    private final OllamaConfig ollamaConfig;
	
	private AIService service;
	@Autowired
	private MCPOrchestrator orches;

    AIController(OllamaConfig ollamaConfig ,AIService service) {
        this.ollamaConfig = ollamaConfig;
        this.service = service;
    }
	
//	@PostMapping("/ask")
//	public ResponseEntity<Map<String ,String>> ask (@RequestBody Map<String ,String> input) throws Exception{
//		String query = isnput.get("query");
//		System.out.println("fvfevfevef"+query);
//		return ResponseEntity.ok(service.handleQuery(query));
//	}
	
	 @PostMapping("/ask")
	    public Object ask(@RequestBody Map<String, String> body) {
	        return orches.handleUserQuery(body.get("query"));
	    }

}
