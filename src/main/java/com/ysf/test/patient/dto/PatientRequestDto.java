package com.ysf.test.patient.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PatientRequestDto(


        @Size(max = 100, message = "le nom ne peut depasser 100 cc")
        @NotBlank(message = "le nom est obligatoire")
        String nom,

        @NotBlank(message = "le prenom est obligratoir")
        @Size(min=3 , max = 100 , message =  "le prenom ne peut depasser 100 cc ")
        String prenom,
        @Past(message = "la date doit etre dans le passé")
        @NotNull(message =" la date de naissance est obligatoire")
        LocalDate dateNaissance,

        @Email(message = "format d'email invalide ")
        String email


) {

}
