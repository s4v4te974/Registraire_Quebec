package com.registraire.main.models.dto;

import java.time.LocalDate;

public record FusionScissionRecord(String id, String neq, String neqAssujRel,
                                   String denomnSoc, String codRelaAssuj, LocalDate datEfctvt,
                                   char indDisp, String lign1Adr, String lign2Adr,
                                   String lign3Adr, String lign4Adr,
                                   EntrepriseRecord entreprise) {
}
