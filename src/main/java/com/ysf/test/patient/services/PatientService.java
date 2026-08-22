package com.ysf.test.patient.services;

import com.ysf.test.patient.dto.PatientRequestDto;
import com.ysf.test.patient.dto.PatientResponseDto;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PatientService {
    PatientResponseDto creerPatient(PatientRequestDto dto);
    PatientResponseDto trouverParId(Long id ) throws Exception;

    Page<PatientResponseDto> rechercher(String nom, Pageable pageable);

    PatientResponseDto modifierPatient(Long id, PatientResponseDto dto) throws Exception;

    void supprimerPatient(Long id) throws Exception;
}
