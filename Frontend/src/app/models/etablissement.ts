import { Entreprise } from "./entreprise";

export interface Etablissement {
    id: string | null | undefined;
    neq: string | null | undefined;
    noSufEtab: number | null | undefined;
    indEtabPrinc: string | null | undefined;
    indSalonBronz: string | null | undefined;
    indVenteTabacDetl: string | null | undefined;
    indDisp: string | null | undefined;
    lign1Adr: string | null | undefined;
    lign2Adr: string | null | undefined;
    lign3Adr: string | null | undefined;
    lign4Adr: string | null | undefined;
    codActEcon: string | null | undefined;
    descActEconEtab: string | null | undefined;
    noActEconEtab: number | null | undefined;
    codActEcon2: string | null | undefined;
    descActEconEtab2: string | null | undefined;
    noActEconEtab2: number | null | undefined;
    nomEtab: string | null | undefined;
    entreprise : Entreprise | null | undefined;
}
