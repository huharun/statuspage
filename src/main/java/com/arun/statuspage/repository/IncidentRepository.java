// Location: src/main/java/com/arun/statuspage/repository/IncidentRepository.java
package com.arun.statuspage.repository;

import com.arun.statuspage.model.Incident;
import com.arun.statuspage.model.IncidentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findAllByOrderByCreatedAtDesc();
    List<Incident> findByStatusNotOrderByCreatedAtDesc(IncidentStatus status);
}