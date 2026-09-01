package com.spring.aiengineeringagent.tool;

import com.spring.aiengineeringagent.entity.IncidentSeverity;
import com.spring.aiengineeringagent.entity.IncidentStatus;
import com.spring.aiengineeringagent.model.IncidentResponse;
import com.spring.aiengineeringagent.service.IncidentService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IncidentTool implements AgentTool {

    private final IncidentService incidentService;

    public IncidentTool(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @Override
    public String getName() {
        return "incident-tool";
    }

    @Override
    public String getDescription() {
        return "Read-only tools for retrieving real engineering incident data from the incident database.";
    }

    @Tool(
            description = """
        Retrieve all incidents whose status is OPEN.
        Return only incidents that actually exist in the database.
        """
    )
    public List<IncidentResponse> findOpenIncidents() {

        return incidentService.getIncidentsByStatus(
                IncidentStatus.OPEN
        );
    }

    @Tool(
            description = """
        Retrieve all incidents whose severity is CRITICAL.
        Return only incidents that actually exist in the database.
        """
    )
    public List<IncidentResponse> findCriticalIncidents() {

        return incidentService.getIncidentsBySeverity(
                IncidentSeverity.CRITICAL
        );
    }

    @Tool(
            description = """
        Find incidents for ONE SPECIFIC service.

        REQUIRED parameter: serviceName.

        Only use this tool when the user explicitly names
        a particular service.

        Examples:
        "incidents for payment-service"
        "payment service incidents"

        Do NOT use this tool for general incident analysis
        or questions asking which services are affected.
        """
    )
    public List<IncidentResponse> findIncidentsByService(
            String serviceName) {

        String normalizedServiceName = serviceName
                .trim()
                .toLowerCase()
                .replace(" ", "-");

        return incidentService.getIncidentsByService(
                normalizedServiceName
        );
    }

    @Tool(
            description = """
        Retrieve exactly one incident using its database ID.
        Return the actual incident from the database.
        Never invent an incident ID.
        """
    )
    public IncidentResponse getIncident(Long id) {

        return incidentService.getIncidentById(id);
    }


    public IncidentResponse updateIncidentStatus(
            Long id,
            IncidentStatus status) {

        return incidentService.updateStatus(id, status);
    }

    @Tool(
            description = """
        Get ALL incidents from the database.

        IMPORTANT:
        This tool takes NO parameters.

        Use this tool when the user asks:
        - Analyze all incidents
        - Summarize current incidents
        - Which services are affected
        - Give an overview of incidents
        - Give incident statistics

        Do NOT use findIncidentsByService for these requests
        unless the user explicitly provides a specific service name.
        """
    )
    public List<IncidentResponse> getAllIncidents() {

        return incidentService.getAllIncidents();
    }
}