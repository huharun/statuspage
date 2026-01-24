// Location: src/main/java/com/arun/statuspage/controller/StatusController.java
package com.arun.statuspage.controller;

import com.arun.statuspage.model.IncidentStatus;
import com.arun.statuspage.model.ServiceStatus;
import com.arun.statuspage.repository.IncidentRepository;
import com.arun.statuspage.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatusController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private IncidentRepository incidentRepository;

    @GetMapping("/")
    public String index(Model model) {
        var services = serviceRepository.findAllByOrderByDisplayOrderAsc();
        var activeIncidents = incidentRepository.findByStatusNotOrderByCreatedAtDesc(IncidentStatus.RESOLVED);

        // Calculate overall status
        ServiceStatus overallStatus = ServiceStatus.OPERATIONAL;
        if (!services.isEmpty()) {
            boolean hasMajor = services.stream().anyMatch(s -> s.getStatus() == ServiceStatus.MAJOR_OUTAGE);
            boolean hasPartial = services.stream().anyMatch(s -> s.getStatus() == ServiceStatus.PARTIAL_OUTAGE);
            boolean hasDegraded = services.stream().anyMatch(s -> s.getStatus() == ServiceStatus.DEGRADED_PERFORMANCE);

            if (hasMajor) {
                overallStatus = ServiceStatus.MAJOR_OUTAGE;
            } else if (hasPartial) {
                overallStatus = ServiceStatus.PARTIAL_OUTAGE;
            } else if (hasDegraded) {
                overallStatus = ServiceStatus.DEGRADED_PERFORMANCE;
            }
        }

        model.addAttribute("services", services);
        model.addAttribute("activeIncidents", activeIncidents);
        model.addAttribute("overallStatus", overallStatus);

        return "index";
    }

    @GetMapping("/history")
    public String history(Model model) {
        var allIncidents = incidentRepository.findAllByOrderByCreatedAtDesc();
        model.addAttribute("incidents", allIncidents);
        return "history";
    }
}