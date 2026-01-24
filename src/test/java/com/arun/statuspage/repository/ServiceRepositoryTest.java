// Location: src/test/java/com/arun/statuspage/repository/ServiceRepositoryTest.java
package com.arun.statuspage.repository;

import com.arun.statuspage.model.Service;
import com.arun.statuspage.model.ServiceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ServiceRepositoryTest {

    @Autowired
    private ServiceRepository serviceRepository;

    @Test
    public void testSaveAndFindService() {
        Service service = new Service("Test API", "Test Description", 1);
        service.setStatus(ServiceStatus.OPERATIONAL);

        Service saved = serviceRepository.save(service);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Test API");
        assertThat(saved.getStatus()).isEqualTo(ServiceStatus.OPERATIONAL);
    }

    @Test
    public void testFindAllByOrderByDisplayOrderAsc() {
        Service service1 = new Service("Service A", "Description A", 2);
        Service service2 = new Service("Service B", "Description B", 1);
        Service service3 = new Service("Service C", "Description C", 3);

        serviceRepository.save(service1);
        serviceRepository.save(service2);
        serviceRepository.save(service3);

        List<Service> services = serviceRepository.findAllByOrderByDisplayOrderAsc();

        assertThat(services).hasSize(3);
        assertThat(services.get(0).getName()).isEqualTo("Service B");
        assertThat(services.get(1).getName()).isEqualTo("Service A");
        assertThat(services.get(2).getName()).isEqualTo("Service C");
    }

    @Test
    public void testUpdateServiceStatus() {
        Service service = new Service("Web App", "Main website", 1);
        service.setStatus(ServiceStatus.OPERATIONAL);
        Service saved = serviceRepository.save(service);

        saved.setStatus(ServiceStatus.MAJOR_OUTAGE);
        Service updated = serviceRepository.save(saved);

        assertThat(updated.getStatus()).isEqualTo(ServiceStatus.MAJOR_OUTAGE);
    }
}