package com.spring.aiengineeringagent.controller;

import com.spring.aiengineeringagent.entity.IncidentSeverity;
import com.spring.aiengineeringagent.entity.IncidentStatus;
import com.spring.aiengineeringagent.model.IncidentRequest;
import com.spring.aiengineeringagent.model.IncidentResponse;
import com.spring.aiengineeringagent.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IncidentResponse createIncident(
            @Valid @RequestBody IncidentRequest request) {

        return incidentService.createIncident(request);
    }

    @GetMapping
    public List<IncidentResponse> getAllIncidents() {

        return incidentService.getAllIncidents();
    }

    @GetMapping("/{id}")
    public IncidentResponse getIncidentById(
            @PathVariable Long id) {

        return incidentService.getIncidentById(id);
    }

    @GetMapping("/status/{status}")
    public List<IncidentResponse> getByStatus(
            @PathVariable IncidentStatus status) {

        return incidentService.getIncidentsByStatus(status);
    }

    @GetMapping("/severity/{severity}")
    public List<IncidentResponse> getBySeverity(
            @PathVariable IncidentSeverity severity) {

        return incidentService.getIncidentsBySeverity(severity);
    }

    @GetMapping("/service/{serviceName}")
    public List<IncidentResponse> getByService(
            @PathVariable String serviceName) {

        return incidentService.getIncidentsByService(serviceName);
    }

    @PutMapping("/{id}/status")
    public IncidentResponse updateStatus(
            @PathVariable Long id,
            @RequestParam IncidentStatus status) {

        return incidentService.updateStatus(id, status);
    }
}