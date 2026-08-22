package com.ysf.test.patient.entities;

import com.ysf.test.rendezVous.entities.RendezVousEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long id;

    @Column(nullable = false , length = 100)
    private String prenom ;

    @Column(nullable = false , length = 100)
    private String nom  ;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance ;

    @Column(unique = true )
    private String email ;

    @OneToMany(mappedBy = "patient" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<RendezVousEntity> rendezVous = new ArrayList<>();
}
