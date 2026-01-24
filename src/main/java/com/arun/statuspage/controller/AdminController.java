// Location: src/main/java/com/arun/statuspage/controller/AdminController.java
package com.arun.statuspage.controller;

import com.arun.statuspage.model.*;
import com.arun.statuspage.repository.IncidentRepository;
import com.arun.statuspage.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private IncidentRepository incidentRepository;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("services", serviceRepository.findAllByOrderByDisplayOrderAsc());
        model.addAttribute("incidents", incidentRepository.findAllByOrderByCreatedAtDesc());
        return "admin/dashboard";
    }

    @GetMapping("/services/new")
    public String newService(Model model) {
        model.addAttribute("service", new Service());
        return "admin/service-form";
    }

    @GetMapping("/services/edit/{id}")
    public String editService(@PathVariable Long id, Model model) {
        var service = serviceRepository.findById(id).orElseThrow();
        model.addAttribute("service", service);
        model.addAttribute("statuses", ServiceStatus.values());
        return "admin/service-form";
    }

    @PostMapping("/services/save")
    public String saveService(@ModelAttribute Service service) {
        serviceRepository.save(service);
        return "redirect:/admin";
    }

    @PostMapping("/services/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
        }
        return "redirect:/admin";
    }

    @GetMapping("/incidents/new")
    public String newIncident(Model model) {
        model.addAttribute("incident", new Incident());
        model.addAttribute("services", serviceRepository.findAll());
        model.addAttribute("severities", IncidentSeverity.values());
        return "admin/incident-form";
    }

    @GetMapping("/incidents/edit/{id}")
    public String editIncident(@PathVariable Long id, Model model) {
        var incident = incidentRepository.findById(id).orElseThrow();
        model.addAttribute("incident", incident);
        model.addAttribute("services", serviceRepository.findAll());
        model.addAttribute("statuses", IncidentStatus.values());
        model.addAttribute("severities", IncidentSeverity.values());
        return "admin/incident-form";
    }

    @PostMapping("/incidents/save")
    public String saveIncident(@ModelAttribute Incident incident, @RequestParam Long serviceId) {
        var service = serviceRepository.findById(serviceId).orElseThrow();
        incident.setAffectedService(service);
        incidentRepository.save(incident);
        return "redirect:/admin";
    }

    @PostMapping("/incidents/delete/{id}")
    public String deleteIncident(@PathVariable Long id) {
        if (incidentRepository.existsById(id)) {
            incidentRepository.deleteById(id);
        }
        return "redirect:/admin";
    }
}