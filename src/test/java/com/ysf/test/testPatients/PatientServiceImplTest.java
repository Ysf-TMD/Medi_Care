package com.ysf.test.testPatients;

import com.ysf.test.patient.Mapper.PatientMapper;
import com.ysf.test.patient.dto.PatientRequestDto;
import com.ysf.test.patient.dto.PatientResponseDto;
import com.ysf.test.patient.entities.PatientEntity;
import com.ysf.test.patient.repository.PatientRepository;
import com.ysf.test.patient.services.PatientServiceImp;
import com.ysf.test.utils.exceptions.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @Mock
    private PatientMapper patientMapper;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImp patientServiceImp;


    @Test
    void creerPatient_devraitRetournerLePatientCreer() {
        // given
        PatientRequestDto dto = new PatientRequestDto("Dupont", "marie", LocalDate.of(1990, 1, 1), "marie@email.com");
        PatientEntity entity = new PatientEntity();
        PatientEntity savedEntity = new PatientEntity();
        savedEntity.setId(1L);
        PatientResponseDto attendu = new PatientResponseDto(1L, "Dupont", "marie", LocalDate.of(1990, 1, 1), "marie@email.com");

        when(patientMapper.toEntity(dto)).thenReturn(entity);
        when(patientRepository.save(entity)).thenReturn(savedEntity);
        when(patientMapper.toDto(savedEntity)).thenReturn(attendu);


        // when
        PatientResponseDto res = patientServiceImp.creerPatient(dto);

        //Then
        Assertions.assertThat(res.id()).isEqualTo(1L) ;
        verify(patientRepository, times(1)).save(entity);
    }


    @Test
    void trouverParId_quandInexistant_leverException(){
        when (patientRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(Exception.class , ()->patientServiceImp.trouverParId(99L));
    }
}
