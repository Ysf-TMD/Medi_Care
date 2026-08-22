package com.ysf.test.patient.dto;

import java.time.LocalDate;

public record PatientResponseDto(
        Long id ,
        String nom ,
        String prenom ,
        LocalDate dateNaissance ,
        String email
) {
}
