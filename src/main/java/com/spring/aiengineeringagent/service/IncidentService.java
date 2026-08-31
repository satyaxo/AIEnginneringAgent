package com.spring.aiengineeringagent.service;

import com.spring.aiengineeringagent.entity.Incident;
import com.spring.aiengineeringagent.entity.IncidentSeverity;
import com.spring.aiengineeringagent.entity.IncidentStatus;
import com.spring.aiengineeringagent.model.IncidentRequest;
import com.spring.aiengineeringagent.model.IncidentResponse;
import com.spring.aiengineeringagent.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public IncidentResponse createIncident(IncidentRequest request) {

        Incident incident = new Incident(
                request.getTitle(),
                request.getDescription(),
                request.getSeverity(),
                request.getStatus(),
                request.getServiceName()
        );

        Incident savedIncident = incidentRepository.save(incident);

        return IncidentResponse.from(savedIncident);
    }

    public List<IncidentResponse> getAllIncidents() {

        return incidentRepository.findAll()
                .stream()
                .map(IncidentResponse::from)
                .toList();
    }

    public IncidentResponse getIncidentById(Long id) {

        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Incident not found with id: " + id
                        ));

        return IncidentResponse.from(incident);
    }

    public List<IncidentResponse> getIncidentsByStatus(
            IncidentStatus status) {

        return incidentRepository.findByStatus(status)
                .stream()
                .map(IncidentResponse::from)
                .toList();
    }

    public List<IncidentResponse> getIncidentsBySeverity(
            IncidentSeverity severity) {

        return incidentRepository.findBySeverity(severity)
                .stream()
                .map(IncidentResponse::from)
                .toList();
    }

    public List<IncidentResponse> getIncidentsByService(
            String serviceName) {

        return incidentRepository.findByServiceName(serviceName)
                .stream()
                .map(IncidentResponse::from)
                .toList();
    }

    public IncidentResponse updateStatus(
            Long id,
            IncidentStatus status) {

        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Incident not found with id: " + id
                        ));

        incident.setStatus(status);

        Incident updatedIncident =
                incidentRepository.save(incident);

        return IncidentResponse.from(updatedIncident);
    }
}