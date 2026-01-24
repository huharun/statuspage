// Location: src/main/java/com/arun/statuspage/model/Service.java
package com.arun.statuspage.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ServiceStatus status = ServiceStatus.OPERATIONAL;

    private LocalDateTime lastChecked;

    @Column(nullable = false)
    private Integer displayOrder = 0;

    public Service() {
        this.lastChecked = LocalDateTime.now();
    }

    public Service(String name, String description, Integer displayOrder) {
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
        this.status = ServiceStatus.OPERATIONAL;
        this.lastChecked = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ServiceStatus getStatus() { return status; }
    public void setStatus(ServiceStatus status) {
        this.status = status;
        this.lastChecked = LocalDateTime.now();
    }

    public LocalDateTime getLastChecked() { return lastChecked; }
    public void setLastChecked(LocalDateTime lastChecked) { this.lastChecked = lastChecked; }

    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
}