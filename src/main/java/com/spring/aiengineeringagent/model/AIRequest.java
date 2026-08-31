package com.spring.aiengineeringagent.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AIRequest {

    @NotBlank(message = "Question cannot be empty")
    @Size(max = 2000, message = "Question cannot exceed 2000 characters")
    private String question;

    public AIRequest() {
    }

    public AIRequest(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}