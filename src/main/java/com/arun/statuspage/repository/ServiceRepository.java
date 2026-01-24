// Location: src/main/java/com/arun/statuspage/repository/ServiceRepository.java
package com.arun.statuspage.repository;

import com.arun.statuspage.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByOrderByDisplayOrderAsc();
}