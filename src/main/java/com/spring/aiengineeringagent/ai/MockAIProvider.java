package com.spring.aiengineeringagent.ai;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        name = "ai.provider",
        havingValue = "mock",
        matchIfMissing = true
)
public class MockAIProvider implements AIProvider {

    @Override
    public String generateResponse(String question) {

        return "Mock AI response generated using prompt: " + question;
    }
}