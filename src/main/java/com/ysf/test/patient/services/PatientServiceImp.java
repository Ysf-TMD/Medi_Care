package com.ysf.test.patient.services;


import com.ysf.test.patient.Mapper.PatientMapper;
import com.ysf.test.patient.dto.PatientRequestDto;
import com.ysf.test.patient.dto.PatientResponseDto;
import com.ysf.test.patient.entities.PatientEntity;
import com.ysf.test.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImp implements PatientService{
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper ;


    @Override
    public PatientResponseDto creerPatient(PatientRequestDto dto) {
        PatientEntity entity = patientMapper.toEntity(dto);
        PatientEntity sauvegarder = patientRepository.save(entity);
        return patientMapper.toDto(sauvegarder);
    }

    @Transactional(readOnly = true)
    @Override
    public PatientResponseDto trouverParId(Long id) throws Exception {
        PatientEntity patient = patientRepository.findById(id)
                .orElseThrow(()->new Exception("Patient not found by id :  "+ id ))
                ;
        return patientMapper.toDto(patient);


    }

    @Override
    public Page<PatientResponseDto > rechercher( String  nom  , Pageable pageable  ){
        Page<PatientEntity>page = (nom == null || nom.isBlank())
                ? patientRepository.findAll(pageable)
                : patientRepository.findByNomContainingIgnoreCase(nom , pageable)
                ;
        return page.map(patientMapper::toDto);
    }




    @Override
    public PatientResponseDto modifierPatient(Long id , PatientResponseDto dto) throws Exception {
        PatientEntity exist = patientRepository.findById(id)
                .orElseThrow(()->new Exception("Patient introuvable " + id ));
        exist.setNom(dto.nom());
        exist.setPrenom(dto.prenom());
        exist.setEmail(dto.email());
        exist.setDateNaissance(dto.dateNaissance());
        return patientMapper.toDto(patientRepository.save(exist));
    }

    @Override
    public void supprimerPatient(Long id) throws Exception {
        if(!patientRepository.existsById(id)){
            throw new Exception("not found " + id  ) ;
        }
        patientRepository.deleteById(id);
    }


}
