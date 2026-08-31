package com.spring.aiengineeringagent.ai;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String buildPrompt(String question) {

        return """
                You are an AI Engineering Assistant.

                Answer the user's question clearly and accurately.

                User question:
                %s
                """.formatted(question);
    }
}