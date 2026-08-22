package com.ysf.test.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record PatientRequestDto(


        @NotBlank String nom ,
        @NotBlank String prenom ,
        @Past LocalDate dateNaissance ,
        @Email String email

) {

}
