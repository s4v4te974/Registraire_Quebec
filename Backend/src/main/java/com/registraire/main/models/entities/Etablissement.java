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

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "etablissement")
public class Etablissement {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NEQ")
    private String neq;

    @Column(name = "NO_SUF_ETAB")
    private int noSufEtab;

    @Column(name = "IND_ETAB_PRINC")
    private char indEtabPrinc;

    @Column(name = "IND_SALON_BRONZ")
    private char indSalonBronz;

    @Column(name = "IND_VENTE_TABAC_DETL")
    private char indVenteTabacDetl;

    @Column(name = "IND_DISP")
    private char indDisp;

    @Column(name = "LIGN1_ADR")
    private String lign1Adr;

    @Column(name = "LIGN2_ADR")
    private String lign2Adr;

    @Column(name = "LIGN3_ADR")
    private String lign3Adr;

    @Column(name = "LIGN4_ADR")
    private String lign4Adr;

    @Column(name = "COD_ACT_ECON")
    private int codActEcon;

    @Column(name = "DESC_ACT_ECON_ETAB")
    private String descActEconEtab;

    @Column(name = "NO_ACT_ECON_ETAB")
    private int noActEconEtab;

    @Column(name = "COD_ACT_ECON2")
    private int codActEcon2;

    @Column(name = "DESC_ACT_ECON_ETAB2")
    private String descActEconEtab2;

    @Column(name = "NO_ACT_ECON_ETAB2")
    private int noActEconEtab2;

    @Column(name = "NOM_ETAB")
    private String nomEtab;

    @ManyToOne
    @JoinColumn(name = "NEQ", referencedColumnName = "NEQ", insertable = false, updatable = false)
    private Entreprise entreprise;
}
