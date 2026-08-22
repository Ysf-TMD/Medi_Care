package com.ysf.test.patient.Mapper;

import com.ysf.test.patient.dto.PatientRequestDto;
import com.ysf.test.patient.dto.PatientResponseDto;
import com.ysf.test.patient.entities.PatientEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientEntity toEntity(PatientRequestDto dto ) ;
    PatientResponseDto toDto(PatientEntity entity) ;
}
