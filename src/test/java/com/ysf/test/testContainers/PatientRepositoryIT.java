package com.ysf.test.testContainers;

import com.ysf.test.patient.entities.PatientEntity;
import com.ysf.test.patient.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Testcontainers
public class PatientRepositoryIT {


    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16-alpine");


    @DynamicPropertySource
    static void configurerProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);

    }
    @Autowired
    private PatientRepository patientRepository;

    @Test
    void save_devraitPersisterLePatient(){
        PatientEntity patient= new PatientEntity();
        patient.setNom("martin");
        patient.setEmail("m@user.com");


        PatientEntity savedPatient= patientRepository.save(patient);
        assertThat(savedPatient.getId()).isNotNull();
    }
}
