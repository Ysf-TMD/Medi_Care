package com.ysf.test.rendezVous.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RendezVousRequestDto(
        @NotNull
        Long patientId,
        @NotNull
        Long medecinId ,
        @Future(message = "le rendez-vous doit etre dans le future")
        @NotNull
        LocalDate dateHeur
) {
}
