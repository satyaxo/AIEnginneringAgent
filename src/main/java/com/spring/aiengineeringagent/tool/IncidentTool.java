package com.spring.aiengineeringagent.tool;

import com.spring.aiengineeringagent.entity.IncidentSeverity;
import com.spring.aiengineeringagent.entity.IncidentStatus;
import com.spring.aiengineeringagent.model.IncidentResponse;
import com.spring.aiengineeringagent.service.IncidentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IncidentTool {

    private final IncidentService incidentService;

    public IncidentTool(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    public List<IncidentResponse> findOpenIncidents() {

        return incidentService.getIncidentsByStatus(
                IncidentStatus.OPEN
        );
    }

    public List<IncidentResponse> findCriticalIncidents() {

        return incidentService.getIncidentsBySeverity(
                IncidentSeverity.CRITICAL
        );
    }

    public List<IncidentResponse> findIncidentsByService(
            String serviceName) {

        return incidentService.getIncidentsByService(
                serviceName
        );
    }

    public IncidentResponse getIncident(Long id) {

        return incidentService.getIncidentById(id);
    }
}