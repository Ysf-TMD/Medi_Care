package com.ysf.test.medcine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medcines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedcinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

}
