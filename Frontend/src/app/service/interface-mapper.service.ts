import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InterfaceMapperService {

  constructor() { }

  mapEntreprise(entreprise: any) {
    return {
      neq: entreprise.neq,
      indFail: entreprise.indFail,
      datImmat: entreprise.datImmat,
      codRegimJuri: entreprise.codRegimJuri,
      codIntvalEmploQue: entreprise.codIntvalEmploQue,
      datCessPrevu: entreprise.datCessPrevu,
      codStatImmat: entreprise.codStatImmat,
      codFormeJuri: entreprise.codFormeJuri,
      datStatImmat: entreprise.datStatImmat,
      codRegimJuriConsti: entreprise.codRegimJuriConsti,
      datDepoDeclr: entreprise.datDepoDeclr,
      anDecl: entreprise.anDecl,
      anProd: entreprise.anProd,
      datLimitProd: entreprise.datLimitProd,
      anProdPre: entreprise.anProdPre,
      datLimitProdPre: entreprise.datLimitProdPre,
      datMajIndexNom: entreprise.datMajIndexNom,
      codActEconCae: entreprise.codActEconCae,
      noActEconAssuj: entreprise.noActEconAssuj,
      descActEconAssuj: entreprise.descActEconAssuj,
      codActEconCae2: entreprise.codActEconCae2,
      noActEconAssuj2: entreprise.noActEconAssuj2,
      descActEconAssuj2: entreprise.descActEconAssuj2,
      nomLoctConsti: entreprise.nomLoctConsti,
      datConsti: entreprise.datConsti,
      indConvenUnmnActnr: entreprise.indConvenUnmnActnr,
      indRetToutPouvr: entreprise.indRetToutPouvr,
      indLimitResp: entreprise.indLimitResp,
      datDebResp: entreprise.datDebResp,
      datFinResp: entreprise.datFinResp,
      objetSoc: entreprise.objetSoc,
      noMtrVolont: entreprise.noMtrVolont,
      adrDomclAdrDisp: entreprise.adrDomclAdrDisp,
      adrDomclLign1Adr: entreprise.adrDomclLign1Adr,
      adrDomclLign2Adr: entreprise.adrDomclLign2Adr,
      adrDomclLign3Adr: entreprise.adrDomclLign3Adr,
      adrDomclLign4Adr: entreprise.adrDomclLign4Adr
    }
  }

  mapEtablissement(etablissement: any) {
    return {
      id: etablissement?.id,
      neq: etablissement?.neq,
      noSufEtab: etablissement?.noSufEtab,
      indEtabPrinc: etablissement?.indEtabPrinc,
      indSalonBronz: etablissement?.indSalonBronz,
      indVenteTabacDetl: etablissement?.indVenteTabacDetl,
      indDisp: etablissement?.indDisp,
      lign1Adr: etablissement?.lign1Adr,
      lign2Adr: etablissement?.lign2Adr,
      lign3Adr: etablissement?.lign3Adr,
      lign4Adr: etablissement?.lign4Adr,
      codActEcon: etablissement?.codActEcon,
      descActEconEtab: etablissement?.descActEconEtab,
      noActEconEtab: etablissement?.noActEconEtab,
      codActEcon2: etablissement?.codActEcon2,
      descActEconEtab2: etablissement?.descActEconEtab2,
      noActEconEtab2: etablissement?.noActEconEtab2,
      nomEtab: etablissement?.nomEtab,
      Entreprise: etablissement?.entreprise
    }
  }

  mapFusionsScissions(fusci: any) {
    return {
      id: fusci.id,
      neq: fusci.neq,
      neqAssujRel: fusci.neqAssujRel,
      denomnSoc: fusci.denomnSoc,
      codRelaAssuj: fusci.codRelaAssuj,
      datEfctvt: fusci.datEfctvt,
      indDisp: fusci.indDisp,
      lign1Adr: fusci.lign1Adr,
      lign2Adr: fusci.lign2Adr,
      lign3Adr: fusci.lign3Adr,
      lign4Adr: fusci.lign4Adr,
      Entreprise: fusci?.entreprise
    }
  }

  mapNom(nom: any) {
    return {
      id: nom.id,
      neq: nom.neq,
      nomAssuj: nom.nomAssuj,
      nomAssujLangEtrng: nom.nomAssujLangEtrng,
      statNom: nom.statNom,
      typNomAssuj: nom.typNomAssuj,
      datInitNomAssuj: nom.datInitNomAssuj,
      datFinNomAssuj: nom.datFinNomAssuj,
      Entreprise: nom?.entreprise
    }
  }

  mapContiTransfo(contiTransfo: any) {
    return {
      id: contiTransfo.id,
      neq: contiTransfo.neq,
      codTypChang: contiTransfo.codTypChang,
      codRegimJuri: contiTransfo.codRegimJuri,
      autrRegimJuri: contiTransfo.autrRegimJuri,
      nomLoct: contiTransfo.nomLoct,
      datEfctvt: contiTransfo.datEfctvt,
      Entreprise: contiTransfo?.entreprise
    }
  }

  mapToEtabMap(etab?: any) {
    return {
      nomEtab: etab.nomEtab,
      adresse: etab.adresse,
      localite: etab.localite,
      localite2: etab.localite2,
      zipcode: etab.zipcode,
      xCoordinate: etab.xCoordinate,
      yCoordinate: etab.yCoordinate,
      code: etab.code,
      codeValue: etab.codeValue
    }
  }
}
