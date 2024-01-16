package com.registraire.main.mapper.utils;

import com.registraire.main.models.dto.EntrepriseRecord;
import com.registraire.main.models.entities.Entreprise;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class EntrepriseMapperUtils {

    private EntrepriseMapperUtils() {
    }

    public static EntrepriseRecord mapToEntrepriseRecord(Entreprise entreprise) {
        return new EntrepriseRecord(
                entreprise.getNeq(),
                entreprise.getIndFail(),
                entreprise.getDatImmat(),
                entreprise.getCodRegimJuri(),
                entreprise.getCodIntvalEmploQue(),
                entreprise.getDatCessPrevu(),
                entreprise.getCodStatImmat(),
                entreprise.getCodFormeJuri(),
                entreprise.getDatStatImmat(),
                entreprise.getCodRegimJuriConsti(),
                entreprise.getDatDepoDeclr(),
                entreprise.getAnDecl(),
                entreprise.getAnProd(),
                entreprise.getDatLimitProd(),
                entreprise.getAnProdPre(),
                entreprise.getDatLimitProdPre(),
                entreprise.getDatMajIndexNom(),
                entreprise.getCodActEconCae(),
                entreprise.getNoActEconAssuj(),
                entreprise.getDescActEconAssuj(),
                entreprise.getCodActEconCae2(),
                entreprise.getNoActEconAssuj2(),
                entreprise.getDescActEconAssuj2(),
                entreprise.getNomLoctConsti(),
                entreprise.getDatConsti(),
                entreprise.getIndConvenUnmnActnr(),
                entreprise.getIndRetToutPouvr(),
                entreprise.getIndLimitResp(),
                entreprise.getDatDebResp(),
                entreprise.getDatFinResp(),
                entreprise.getObjetSoc(),
                entreprise.getNoMtrVolont(),
                entreprise.getAdrDomclAdrDisp(),
                entreprise.getAdrDomclLign1Adr(),
                entreprise.getAdrDomclLign2Adr(),
                entreprise.getAdrDomclLign3Adr(),
                entreprise.getAdrDomclLign4Adr(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }
}