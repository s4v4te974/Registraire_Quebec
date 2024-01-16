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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "nom")
public class Nom {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NEQ")
    private String neq;

    @Column(name = "NOM_ASSUJ")
    private String nomAssuj;

    @Column(name = "NOM_ASSUJ_LANG_ETRNG")
    private String nomAssujLangEtrng;

    @Column(name = "STAT_NOM")
    private String statNom;

    @Column(name = "TYP_NOM_ASSUJ")
    private String typNomAssuj;

    @Column(name = "DAT_INIT_NOM_ASSUJ")
    private LocalDate datInitNomAssuj;

    @Column(name = "DAT_FIN_NOM_ASSUJ")
    private LocalDate datFinNomAssuj;

    @ManyToOne
    @JoinColumn(name = "NEQ", referencedColumnName = "NEQ", insertable = false, updatable = false)
    private Entreprise entreprise;
}
