package com.registraire.main.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "continuationtransformation")
public class ContinuationTransformation {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NEQ")
    private String neq;

    @Column(name = "COD_TYP_CHANG")
    private String codTypChang;

    @Column(name = "COD_REGIM_JURI")
    private String codRegimJuri;

    @Column(name = "AUTR_REGIM_JURI")
    private String autrRegimJuri;

    @Column(name = "NOM_LOCLT")
    private String nomLoct;

    @Column(name = "DAT_EFCTVT")
    private LocalDate datEfctvt;

    @ManyToOne
    @JoinColumn(name = "NEQ", referencedColumnName = "NEQ", insertable = false, updatable = false)
    private Entreprise entreprise;
}
