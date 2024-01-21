package com.registraire.main.models.dto.basic;

public record EtablissementRecord(String id, String neq, int noSufEtab,
                                  char indEtabPrinc, char indSalonBronz,
                                  char indVenteTabacDetl, char indDisp,
                                  String lign1Adr, String lign2Adr, String lign3Adr,
                                  String lign4Adr, int codActEcon,
                                  String descActEconEtab, int noActEconEtab,
                                  int codActEcon2, String descActEconEtab2,
                                  int noActEconEtab2, String nomEtab,
                                  EntrepriseRecord entreprise) {
}
