package com.spring.aiengineeringagent.model;

import com.spring.aiengineeringagent.entity.Incident;
import com.spring.aiengineeringagent.entity.IncidentSeverity;
import com.spring.aiengineeringagent.entity.IncidentStatus;

public class IncidentResponse {

    private Long id;
    private String title;
    private String description;
    private IncidentSeverity severity;
    private IncidentStatus status;
    private String serviceName;

    public IncidentResponse() {
    }

    public IncidentResponse(
            Long id,
            String title,
            String description,
            IncidentSeverity severity,
            IncidentStatus status,
            String serviceName) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
        this.serviceName = serviceName;
    }

    public static IncidentResponse from(Incident incident) {

        return new IncidentResponse(
                incident.getId(),
                incident.getTitle(),
                incident.getDescription(),
                incident.getSeverity(),
                incident.getStatus(),
                incident.getServiceName()
        );
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public IncidentSeverity getSeverity() {
        return severity;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public String getServiceName() {
        return serviceName;
    }
}