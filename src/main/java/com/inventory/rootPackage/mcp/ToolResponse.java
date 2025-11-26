package com.inventory.rootPackage.mcp;

import lombok.Data;

@Data
public class ToolResponse {
	
	private String status;
	private String message;
	private Object result;
	
}
