package com.spring.aiengineeringagent.service;

import com.spring.aiengineeringagent.ai.AIProvider;
import com.spring.aiengineeringagent.ai.PromptBuilder;
import com.spring.aiengineeringagent.model.AIResponse;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final AIProvider aiProvider;
    private final PromptBuilder promptBuilder;

    public AIService(
            AIProvider aiProvider,
            PromptBuilder promptBuilder) {

        this.aiProvider = aiProvider;
        this.promptBuilder = promptBuilder;
    }

    public AIResponse askQuestion(String question) {

        String prompt = promptBuilder.buildPrompt(question);

        String answer = aiProvider.generateResponse(prompt);

        return new AIResponse(answer);
    }
}