package com.ysf.test.patient.controller;


import com.ysf.test.patient.dto.PatientRequestDto;
import com.ysf.test.patient.dto.PatientResponseDto;
import com.ysf.test.patient.services.PatientService;
import com.ysf.test.patient.services.PatientServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin("*")
@RequestMapping("/patients")
@Tag(name = "Patients", description = "Gestion des patients")
public class PatientRestController {


    private final PatientService patientService;

    public PatientRestController(PatientService  patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Créer un patient", description = "Crée un nouveau patient dans le système")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Patient créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public ResponseEntity<PatientResponseDto> creer(@Valid @RequestBody PatientRequestDto dto) {
        PatientResponseDto cree = patientService.creerPatient(dto);
        //return ResponseEntity.status(HttpStatus.CREATED).body(cree) ;
        URI location = URI.create("/patients/" + cree.id());
        return ResponseEntity.created(location).body(cree);
    }

    @GetMapping()
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("Medi_care welcome page ( home ) ");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> parId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(patientService.trouverParId(id));
    }

    public ResponseEntity<Page<PatientResponseDto>> lister(
            @RequestParam(required = false) String nom,
            @PageableDefault(size = 20, sort = "name") Pageable pageable
    ) {
        return ResponseEntity.ok(patientService.rechercher(nom, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> modifier(Long id, PatientResponseDto dto) throws Exception {
        return ResponseEntity.ok(patientService.modifierPatient(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> esupprimer(@PathVariable Long id) throws Exception {
        patientService.supprimerPatient(id);
        return ResponseEntity.noContent().build();
    }
}
