import { Entreprise } from "./entreprise";

export interface Nom {
    id: string | null | undefined;
    neq: string | null | undefined;
    nomAssuj: string | null | undefined;
    nomAssujLangEtrng: string | null | undefined;
    statNom: string | null | undefined;
    typNomAssuj: string | null | undefined;
    datInitNomAssuj: Date | null | undefined;
    datFinNomAssuj: Date | null | undefined;
    Entreprise: Entreprise | null | undefined;
}
