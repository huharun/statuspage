// Location: src/main/java/com/arun/statuspage/model/Incident.java
package com.arun.statuspage.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private IncidentStatus status = IncidentStatus.INVESTIGATING;

    @Enumerated(EnumType.STRING)
    private IncidentSeverity severity = IncidentSeverity.MINOR;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service affectedService;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;

    public Incident() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Incident(String title, String description, Service affectedService) {
        this.title = title;
        this.description = description;
        this.affectedService = affectedService;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public IncidentStatus getStatus() { return status; }
    public void setStatus(IncidentStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
        if (status == IncidentStatus.RESOLVED) {
            this.resolvedAt = LocalDateTime.now();
        }
    }

    public IncidentSeverity getSeverity() { return severity; }
    public void setSeverity(IncidentSeverity severity) { this.severity = severity; }

    public Service getAffectedService() { return affectedService; }
    public void setAffectedService(Service affectedService) { this.affectedService = affectedService; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }
}