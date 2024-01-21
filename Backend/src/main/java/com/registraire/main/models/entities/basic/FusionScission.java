package com.registraire.main.models.entities.basic;

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
@Table(name = "fusionscission")
public class FusionScission {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NEQ")
    private String neq;

    @Column(name = "NEQ_ASSUJ_REL")
    private String neqAssujRel;

    @Column(name = "DENOMN_SOC")
    private String denomnSoc;

    @Column(name = "COD_RELA_ASSUJ")
    private String codRelaAssuj;

    @Column(name = "DAT_EFCTVT")
    private LocalDate datEfctvt;

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

    @ManyToOne
    @JoinColumn(name = "NEQ", referencedColumnName = "NEQ", insertable = false, updatable = false)
    private Entreprise entreprise;
}
