package com.spring.aiengineeringagent.controller;

import com.spring.aiengineeringagent.model.AgentRequest;
import com.spring.aiengineeringagent.service.AgentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping
    public String processRequest(
            @Valid @RequestBody AgentRequest request) {

        return agentService.processRequest(
                request.getRequest()
        );
    }
}