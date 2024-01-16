import { Entreprise } from "./entreprise";

export interface ContiTransfo {
    id: string | null | undefined;
    neq: string | null | undefined;
    codTypChang: string | null | undefined;
    codRegimJuri: string | null | undefined;
    autrRegimJuri: string | null | undefined;
    nomLoct: string | null | undefined;
    datEfctvt: Date | null | undefined;
    Entreprise: Entreprise | null | undefined;
}
