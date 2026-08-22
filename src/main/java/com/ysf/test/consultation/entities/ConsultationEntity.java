package com.ysf.test.consultation.entities;

import com.ysf.test.rendezVous.entities.RendezVousEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="consultations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @OneToOne(optional=false , fetch = FetchType.LAZY)
    @JoinColumn(name = "rendezvous_id")
    private RendezVousEntity rendezvous;
}
