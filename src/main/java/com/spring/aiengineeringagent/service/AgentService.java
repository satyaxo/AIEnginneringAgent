package com.spring.aiengineeringagent.service;

import com.spring.aiengineeringagent.model.IncidentResponse;
import com.spring.aiengineeringagent.tool.IncidentTool;
import com.spring.aiengineeringagent.tool.ToolRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    private final ToolRegistry toolRegistry;

    public AgentService(ToolRegistry toolRegistry) {
        this.toolRegistry = toolRegistry;
    }

    public String processRequest(String request) {

        String normalizedRequest = request.toLowerCase().trim();

        IncidentTool incidentTool =
                (IncidentTool) toolRegistry.getTool("incident-tool");

        if (normalizedRequest.contains("open incidents")) {

            List<IncidentResponse> incidents =
                    incidentTool.findOpenIncidents();

            return formatIncidents(incidents);
        }

        if (normalizedRequest.contains("critical incidents")) {

            List<IncidentResponse> incidents =
                    incidentTool.findCriticalIncidents();

            return formatIncidents(incidents);
        }

        return "I don't have a tool available to handle this request yet.";
    }

    private String formatIncidents(
            List<IncidentResponse> incidents) {

        if (incidents.isEmpty()) {
            return "No matching incidents found.";
        }

        StringBuilder response = new StringBuilder();

        response.append("Found ")
                .append(incidents.size())
                .append(" incident(s):\n\n");

        for (IncidentResponse incident : incidents) {

            response.append("Incident #")
                    .append(incident.getId())
                    .append("\n");

            response.append("Title: ")
                    .append(incident.getTitle())
                    .append("\n");

            response.append("Severity: ")
                    .append(incident.getSeverity())
                    .append("\n");

            response.append("Status: ")
                    .append(incident.getStatus())
                    .append("\n");

            response.append("Service: ")
                    .append(incident.getServiceName())
                    .append("\n\n");
        }

        return response.toString();
    }
}