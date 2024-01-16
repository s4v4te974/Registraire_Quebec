package com.registraire.main.models.dto;

public record EtablissementRecord(String id, String neq, int noSufEtab,
                                  char indEtabPrinc, char indSalonBronz,
                                  char indVenteTabacDetl, char indDisp,
                                  String lign1Adr, String lign2Adr, String lign3Adr,
                                  String lign4Adr, String codActEcon,
                                  String descActEconEtab, int noActEconEtab,
                                  String codActEcon2, String descActEconEtab2,
                                  int noActEconEtab2, String nomEtab,
                                  EntrepriseRecord entreprise) {
}
