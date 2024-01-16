import { Entreprise } from "./entreprise";

export interface Fusci {
    id: string | null | undefined;
    neq: string | null | undefined;
    neqAssujRel: string | null | undefined;
    denomnSoc: string | null | undefined;
    codRelaAssuj: string | null | undefined;
    datEfctvt: Date | null | undefined;
    indDisp: string | null | undefined;
    lign1Adr: string | null | undefined;
    lign2Adr: string | null | undefined;
    lign3Adr: string | null | undefined;
    lign4Adr: string | null | undefined;
    Entreprise: Entreprise | null | undefined;
}
