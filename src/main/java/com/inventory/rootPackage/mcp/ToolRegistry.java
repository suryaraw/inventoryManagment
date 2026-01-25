package com.inventory.rootPackage.mcp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class ToolRegistry {
	
	private final Map<String, Function<Object, Object>> tools = new HashMap<>();

    public void registerTool(String name, Function<Object, Object> executor) {
        tools.put(name, executor);
    }

    public Object execute(String name, Object params) {
    	System.out.println("execution  " + params);
        if (!tools.containsKey(name)) {
            throw new RuntimeException("Unknown tool: " + name);
        }
        return tools.get(name).apply(params);
    }

    public boolean exists(String name) {
        return tools.containsKey(name);
    }

}
