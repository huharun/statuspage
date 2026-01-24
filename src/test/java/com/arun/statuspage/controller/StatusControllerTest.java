// Location: src/test/java/com/arun/statuspage/controller/StatusControllerTest.java
package com.arun.statuspage.controller;

import com.arun.statuspage.model.Service;
import com.arun.statuspage.model.ServiceStatus;
import com.arun.statuspage.repository.IncidentRepository;
import com.arun.statuspage.repository.ServiceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StatusController.class)
public class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceRepository serviceRepository;

    @MockBean
    private IncidentRepository incidentRepository;

    @Test
    @WithMockUser
    public void testIndexPage() throws Exception {
        Service service = new Service("API", "Backend API", 1);
        service.setStatus(ServiceStatus.OPERATIONAL);

        when(serviceRepository.findAllByOrderByDisplayOrderAsc()).thenReturn(Arrays.asList(service));
        when(incidentRepository.findByStatusNotOrderByCreatedAtDesc(null)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("services"))
                .andExpect(model().attributeExists("overallStatus"));
    }

    @Test
    @WithMockUser
    public void testHistoryPage() throws Exception {
        when(incidentRepository.findAllByOrderByCreatedAtDesc()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("history"))
                .andExpect(model().attributeExists("incidents"));
    }
}