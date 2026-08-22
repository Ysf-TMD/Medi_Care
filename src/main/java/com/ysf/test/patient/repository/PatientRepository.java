package com.ysf.test.patient.repository;

import com.ysf.test.patient.entities.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Page<PatientEntity> findByNomContainingIgnoreCase(String nom, Pageable pageable);

}
