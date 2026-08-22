package com.ysf.test.rendezVous.entities;

import com.ysf.test.consultation.entities.ConsultationEntity;
import com.ysf.test.medcine.entities.MedcinEntity;
import com.ysf.test.patient.entities.PatientEntity;
import com.ysf.test.rendezVous.enums.StatutRDV;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "rendezvous")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class RendezVousEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id" , nullable = false)
    private PatientEntity patient ;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medcine_id" , nullable = false)
    private MedcinEntity medcine ;


    private LocalDate dateHeur ;

    @Enumerated(EnumType.STRING)
    private StatutRDV status ;

    @OneToOne(mappedBy = "rendezvous" , cascade = CascadeType.ALL)
    private ConsultationEntity consultation ;
}
