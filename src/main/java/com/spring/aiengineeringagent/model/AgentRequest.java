package com.spring.aiengineeringagent.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AgentRequest {

    @NotBlank(message = "Request cannot be empty")
    @Size(max = 2000, message = "Request cannot exceed 2000 characters")
    private String request;

    public AgentRequest() {
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}