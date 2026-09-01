package com.spring.aiengineeringagent.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        name = "ai.provider",
        havingValue = "ollama"
)
public class OllamaAIProvider implements AIProvider {

    private final ChatClient chatClient;

    public OllamaAIProvider(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String generateResponse(String question) {

        return chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }
}