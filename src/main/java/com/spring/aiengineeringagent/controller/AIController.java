package com.spring.aiengineeringagent.controller;

import com.spring.aiengineeringagent.model.AIRequest;
import com.spring.aiengineeringagent.model.AIResponse;
import com.spring.aiengineeringagent.service.AIService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/ask")
    public AIResponse askQuestion(@Valid @RequestBody AIRequest request) {

        return aiService.askQuestion(request.getQuestion());
    }
}