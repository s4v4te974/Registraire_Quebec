package com.registraire.main.models.dto;

import java.time.LocalDate;
import java.util.List;

public record EntrepriseRecord(String neq, char indFail, LocalDate datImmat,
                               String codRegimJuri, String codIntvalEmploQue,
                               LocalDate datCessPrevu, String codStatImmat,
                               String codFormeJuri, LocalDate datStatImmat,
                               String codRegimJuriConsti, LocalDate datDepoDeclr,
                               int anDecl, int anProd, LocalDate datLimitProd,

                               int anProdPre, LocalDate datLimitProdPre, LocalDate datMajIndexNom,

                               String codActEconCae, int noActEconAssuj, String descActEconAssuj,

                               String codActEconCae2, int noActEconAssuj2, String descActEconAssuj2,

                               String nomLoctConsti, LocalDate datConsti, char indConvenUnmnActnr,

                               char indRetToutPouvr, char indLimitResp, LocalDate datDebResp,

                               LocalDate datFinResp, String objetSoc, String noMtrVolont,

                               char adrDomclAdrDisp, String adrDomclLign1Adr,

                               String adrDomclLign2Adr, String adrDomclLign3Adr,

                               String adrDomclLign4Adr, List<EtablissementRecord> etablissements,

                               List<ContinuationTransformationRecord> contiTransfo,

                               List<FusionScissionRecord> fusionScissions,

                               List<NomRecord> noms) {
}