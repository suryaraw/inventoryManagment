package com.inventory.rootPackage.ai;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ai")
public class AIController {

    private final OllamaConfig ollamaConfig;
	
	private AIService service;

    AIController(OllamaConfig ollamaConfig ,AIService service) {
        this.ollamaConfig = ollamaConfig;
        this.service = service;
    }
	
	@PostMapping("/ask")
	public ResponseEntity<Map<String ,String>> ask (@RequestBody Map<String ,String> input){
		String query = input.get("query");
		System.out.println("fvfevfevef"+service.handleQuery(query));
		return ResponseEntity.ok(service.handleQuery(query));
	}

}
