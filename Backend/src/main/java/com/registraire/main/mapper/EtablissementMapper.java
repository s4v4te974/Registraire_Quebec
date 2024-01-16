package com.registraire.main.mapper;

import com.registraire.main.mapper.utils.EntrepriseMapperUtils;
import com.registraire.main.models.dto.EntrepriseRecord;
import com.registraire.main.models.dto.EtablissementRecord;
import com.registraire.main.models.entities.Entreprise;
import com.registraire.main.models.entities.Etablissement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EtablissementMapper {

    EtablissementMapper INSTANCE = Mappers.getMapper(EtablissementMapper.class);

    @Mapping(target = "id", expression = "java(etab.getId().toString())")
    @Mapping(target = "entreprise",source = "entreprise", qualifiedByName = "entrepriseEtab")
    EtablissementRecord mapToEtablissement(Etablissement etab);

    @Named("entrepriseEtab")
    default EntrepriseRecord mapEntrepriseForEtab(Entreprise entreprise) {
        return EntrepriseMapperUtils.mapToEntrepriseRecord(entreprise);
    }
}
