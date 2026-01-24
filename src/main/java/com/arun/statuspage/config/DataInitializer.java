// Location: src/main/java/com/arun/statuspage/config/DataInitializer.java
package com.arun.statuspage.config;

import com.arun.statuspage.model.*;
import com.arun.statuspage.repository.IncidentRepository;
import com.arun.statuspage.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private IncidentRepository incidentRepository;

    @Override
    public void run(String... args) throws Exception {
        if (serviceRepository.count() == 0) {
            Service webApp = new Service("Web Application", "Main web application frontend", 1);
            Service api = new Service("API Server", "RESTful API backend services", 2);
            Service database = new Service("Database", "Primary PostgreSQL database", 3);
            Service cdn = new Service("CDN", "Content delivery network", 4);

            serviceRepository.save(webApp);
            serviceRepository.save(api);
            serviceRepository.save(database);
            serviceRepository.save(cdn);

            Incident incident = new Incident(
                    "Database Connection Issues",
                    "We are experiencing intermittent database connection timeouts. Our team is investigating the root cause.",
                    database
            );
            incident.setSeverity(IncidentSeverity.MINOR);
            incident.setStatus(IncidentStatus.INVESTIGATING);

            incidentRepository.save(incident);

            System.out.println("Sample data initialized!");
            System.out.println("Login credentials - Username: admin | Password: admin123");
        }
    }
}