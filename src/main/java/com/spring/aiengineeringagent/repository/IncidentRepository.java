package com.spring.aiengineeringagent.repository;

import com.spring.aiengineeringagent.entity.Incident;
import com.spring.aiengineeringagent.entity.IncidentSeverity;
import com.spring.aiengineeringagent.entity.IncidentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    List<Incident> findByStatus(IncidentStatus status);

    List<Incident> findBySeverity(IncidentSeverity severity);

    List<Incident> findByServiceName(String serviceName);
}