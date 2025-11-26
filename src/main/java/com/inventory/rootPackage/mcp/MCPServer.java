package com.inventory.rootPackage.mcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mcp")
public class MCPServer {
	
	 @Autowired
	    private ToolRegistry toolRegistry;

	    @PostMapping("/tool")
	    public ToolResponse handleToolCall(@RequestBody ToolRequest request) {

	        ToolResponse response = new ToolResponse();

	        try {
	            Object result = toolRegistry.execute(request.getTool(), request.getParams());
	            response.setStatus("success");
	            response.setResult(result);
	        } catch (Exception e) {
	            response.setStatus("error");
	            response.setMessage(e.getMessage());
	        }

	        return response;
	    }

}
