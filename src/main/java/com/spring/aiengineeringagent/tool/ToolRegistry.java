package com.spring.aiengineeringagent.tool;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToolRegistry {

    private final List<AgentTool> tools;

    public ToolRegistry(List<AgentTool> tools) {
        this.tools = tools;
    }

    public AgentTool getTool(String name) {

        return tools.stream()
                .filter(tool -> tool.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Tool not found: " + name
                        )
                );
    }

    public List<AgentTool> getAllTools() {
        return tools;
    }
}