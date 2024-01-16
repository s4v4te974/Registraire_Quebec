package com.registraire.main.mapper;

import com.registraire.main.models.dto.ContinuationTransformationRecord;
import com.registraire.main.models.dto.EntrepriseRecord;
import com.registraire.main.models.dto.EtablissementRecord;
import com.registraire.main.models.dto.FusionScissionRecord;
import com.registraire.main.models.dto.NomRecord;
import com.registraire.main.models.entities.ContinuationTransformation;
import com.registraire.main.models.entities.Entreprise;
import com.registraire.main.models.entities.Etablissement;
import com.registraire.main.models.entities.FusionScission;
import com.registraire.main.models.entities.Nom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EntrepriseMapper {

    EntrepriseMapper INSTANCE = Mappers.getMapper(EntrepriseMapper.class);

    @Mapping(target = "etablissements", source = "etablissements", qualifiedByName = "mapEntrepriseEtab")
    @Mapping(target = "fusionScissions", source = "fusionScissions", qualifiedByName = "mapEntrepriseFusci")
    @Mapping(target = "noms", source = "noms", qualifiedByName = "mapEntrepriseNoms")
    @Mapping(target = "contiTransfo", source = "contiTransfo", qualifiedByName = "mapEntrepriseConti")
    EntrepriseRecord mapToEntrepriseRecord(Entreprise entreprise);

    @Named("mapEntrepriseEtab")
    default List<EtablissementRecord> mapEntrepriseEtab(List<Etablissement> etab) {
        return etab.stream().map(etablissement ->
                new EtablissementRecord(
                        etablissement.getId().toString(),
                        etablissement.getNeq(),
                        etablissement.getNoSufEtab(),
                        etablissement.getIndEtabPrinc(),
                        etablissement.getIndSalonBronz(),
                        etablissement.getIndVenteTabacDetl(),
                        etablissement.getIndDisp(),
                        etablissement.getLign1Adr(),
                        etablissement.getLign2Adr(),
                        etablissement.getLign3Adr(),
                        etablissement.getLign4Adr(),
                        etablissement.getCodActEcon(),
                        etablissement.getDescActEconEtab(),
                        etablissement.getNoActEconEtab(),
                        etablissement.getCodActEcon2(),
                        etablissement.getDescActEconEtab2(),
                        etablissement.getNoActEconEtab2(),
                        etablissement.getNomEtab(),
                        null)
        ).toList();
    }

    @Named("mapEntrepriseFusci")
    default List<FusionScissionRecord> mapEntrepriseFusci(List<FusionScission> fusci) {
        return fusci.stream().map(fusionScission ->
                new FusionScissionRecord(
                        fusionScission.getId().toString(),
                        fusionScission.getNeq(),
                        fusionScission.getNeqAssujRel(),
                        fusionScission.getDenomnSoc(),
                        fusionScission.getCodRelaAssuj(),
                        fusionScission.getDatEfctvt(),
                        fusionScission.getIndDisp(),
                        fusionScission.getLign1Adr(),
                        fusionScission.getLign2Adr(),
                        fusionScission.getLign3Adr(),
                        fusionScission.getLign4Adr(),
                        null)
        ).toList();
    }

    @Named("mapEntrepriseNoms")
    default List<NomRecord> mapEntrepriseNoms(List<Nom> noms) {
        return noms.stream().map(nom ->
                new NomRecord(
                        nom.getId().toString(),
                        nom.getNeq(),
                        nom.getNomAssuj(),
                        nom.getNomAssujLangEtrng(),
                        nom.getStatNom(),
                        nom.getTypNomAssuj(),
                        nom.getDatInitNomAssuj(),
                        nom.getDatFinNomAssuj(),
                        null)
        ).toList();
    }

    @Named("mapEntrepriseConti")
    default List<ContinuationTransformationRecord> mapEntrepriseConti(List<ContinuationTransformation> contis) {
        return contis.stream().map(continTransfo ->
                new ContinuationTransformationRecord(
                        continTransfo.getId().toString(),
                        continTransfo.getNeq(),
                        continTransfo.getCodTypChang(),
                        continTransfo.getCodRegimJuri(),
                        continTransfo.getAutrRegimJuri(),
                        continTransfo.getNomLoct(),
                        continTransfo.getDatEfctvt(),
                        null)
        ).toList();
    }
}
