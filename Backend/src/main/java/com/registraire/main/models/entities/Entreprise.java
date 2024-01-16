package com.registraire.main.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "entreprise", schema = "public")
public class Entreprise {
    @Id
    @Column(name = "NEQ", nullable = false)
    private String neq;

    @Column(name = "IND_FAIL")
    private char indFail;

    @Column(name = "DAT_IMMAT")
    private LocalDate datImmat;

    @Column(name = "COD_REGIM_JURI")
    private String codRegimJuri;

    @Column(name = "COD_INTVAL_EMPLO_QUE")
    private String codIntvalEmploQue;

    @Column(name = "DAT_CESS_PREVU")
    private LocalDate datCessPrevu;

    @Column(name = "COD_STAT_IMMAT")
    private String codStatImmat;

    @Column(name = "COD_FORME_JURI")
    private String codFormeJuri;

    @Column(name = "DAT_STAT_IMMAT")
    private LocalDate datStatImmat;

    @Column(name = "COD_REGIM_JURI_CONSTI")
    private String codRegimJuriConsti;

    @Column(name = "DAT_DEPO_DECLR")
    private LocalDate datDepoDeclr;

    @Column(name = "AN_DECL")
    private int anDecl;

    @Column(name = "AN_PROD")
    private int anProd;

    @Column(name = "DAT_LIMIT_PROD")
    private LocalDate datLimitProd;

    @Column(name = "AN_PROD_PRE")
    private int anProdPre;

    @Column(name = "DAT_LIMIT_PROD_PRE")
    private LocalDate datLimitProdPre;

    @Column(name = "DAT_MAJ_INDEX_NOM")
    private LocalDate datMajIndexNom;

    @Column(name = "COD_ACT_ECON_CAE")
    private String codActEconCae;

    @Column(name = "NO_ACT_ECON_ASSUJ")
    private int noActEconAssuj;

    @Column(name = "DESC_ACT_ECON_ASSUJ")
    private String descActEconAssuj;

    @Column(name = "COD_ACT_ECON_CAE2")
    private String codActEconCae2;

    @Column(name = "NO_ACT_ECON_ASSUJ2")
    private int noActEconAssuj2;

    @Column(name = "DESC_ACT_ECON_ASSUJ2")
    private String descActEconAssuj2;

    @Column(name = "NOM_LOCLT_CONSTI")
    private String nomLoctConsti;

    @Column(name = "DAT_CONSTI")
    private LocalDate datConsti;

    @Column(name = "IND_CONVEN_UNMN_ACTNR")
    private char indConvenUnmnActnr;

    @Column(name = "IND_RET_TOUT_POUVR")
    private char indRetToutPouvr;

    @Column(name = "IND_LIMIT_RESP")
    private char indLimitResp;

    @Column(name = "DAT_DEB_RESP")
    private LocalDate datDebResp;

    @Column(name = "DAT_FIN_RESP")
    private LocalDate datFinResp;

    @Column(name = "OBJET_SOC")
    private String objetSoc;

    @Column(name = "NO_MTR_VOLONT")
    private String noMtrVolont;

    @Column(name = "ADR_DOMCL_ADR_DISP")
    private char adrDomclAdrDisp;

    @Column(name = "ADR_DOMCL_LIGN1_ADR")
    private String adrDomclLign1Adr;

    @Column(name = "ADR_DOMCL_LIGN2_ADR")
    private String adrDomclLign2Adr;

    @Column(name = "ADR_DOMCL_LIGN3_ADR")
    private String adrDomclLign3Adr;

    @Column(name = "ADR_DOMCL_LIGN4_ADR")
    private String adrDomclLign4Adr;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entreprise")
    private List<Etablissement> etablissements;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entreprise")
    private List<ContinuationTransformation> contiTransfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entreprise")
    private List<FusionScission> fusionScissions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entreprise")
    private List<Nom> noms;

}
