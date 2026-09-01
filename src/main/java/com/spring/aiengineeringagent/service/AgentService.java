package com.spring.aiengineeringagent.service;

import com.spring.aiengineeringagent.model.IncidentResponse;
import com.spring.aiengineeringagent.tool.IncidentTool;
import com.spring.aiengineeringagent.tool.ToolRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AgentService {

    private final ToolRegistry toolRegistry;

    public AgentService(ToolRegistry toolRegistry) {
        this.toolRegistry = toolRegistry;
    }

    public String processRequest(String request) {

        if (request == null || request.isBlank()) {
            return "Please provide a request.";
        }

        String normalizedRequest =
                request.toLowerCase().trim();

        IncidentTool incidentTool =
                (IncidentTool) toolRegistry.getTool("incident-tool");

        // --------------------------------
        // 1. Open incidents
        // --------------------------------

        if (normalizedRequest.contains("open incidents")) {

            List<IncidentResponse> incidents =
                    incidentTool.findOpenIncidents();

            return formatIncidents(incidents);
        }

        // --------------------------------
        // 2. Critical incidents
        // --------------------------------

        if (normalizedRequest.contains("critical incidents")) {

            List<IncidentResponse> incidents =
                    incidentTool.findCriticalIncidents();

            return formatIncidents(incidents);
        }

        // --------------------------------
        // 3. Incidents by service
        // --------------------------------

        if (normalizedRequest.contains("incidents for")
                || normalizedRequest.contains("incidents in")
                || normalizedRequest.contains("incidents from")) {

            String serviceName =
                    extractServiceName(normalizedRequest);

            if (serviceName != null) {

                List<IncidentResponse> incidents =
                        incidentTool.findIncidentsByService(serviceName);

                return formatIncidents(incidents);
            }
        }

        // --------------------------------
        // 4. Get incident by ID
        // --------------------------------

        if (normalizedRequest.contains("incident")) {

            Long incidentId =
                    extractIncidentId(normalizedRequest);

            if (incidentId != null) {

                IncidentResponse incident =
                        incidentTool.getIncident(incidentId);

                return formatIncident(incident);
            }
        }

        // --------------------------------
        // 5. Unsupported request
        // --------------------------------

        return "I don't have a tool available to handle this request yet.";
    }

    private String extractServiceName(String request) {

        Pattern pattern = Pattern.compile(
                "incidents\\s+(?:for|in|from)\\s+([a-zA-Z0-9_-]+)"
        );

        Matcher matcher = pattern.matcher(request);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    private Long extractIncidentId(String request) {

        Pattern pattern = Pattern.compile(
                "incident\\s+#?(\\d+)"
        );

        Matcher matcher = pattern.matcher(request);

        if (matcher.find()) {

            return Long.parseLong(
                    matcher.group(1)
            );
        }

        return null;
    }

    private String formatIncidents(
            List<IncidentResponse> incidents) {

        if (incidents.isEmpty()) {
            return "No matching incidents found.";
        }

        StringBuilder response =
                new StringBuilder();

        response.append("Found ")
                .append(incidents.size())
                .append(" incident(s):\n\n");

        for (IncidentResponse incident : incidents) {

            response.append(formatIncident(incident))
                    .append("\n");
        }

        return response.toString();
    }

    private String formatIncident(
            IncidentResponse incident) {

        if (incident == null) {
            return "Incident not found.";
        }

        StringBuilder response =
                new StringBuilder();

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
                .append("\n");

        return response.toString();
    }
}