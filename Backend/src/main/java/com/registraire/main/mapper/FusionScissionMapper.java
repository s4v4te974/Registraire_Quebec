package com.registraire.main.mapper;

import com.registraire.main.mapper.utils.EntrepriseMapperUtils;
import com.registraire.main.models.dto.basic.EntrepriseRecord;
import com.registraire.main.models.dto.basic.FusionScissionRecord;
import com.registraire.main.models.entities.basic.Entreprise;
import com.registraire.main.models.entities.basic.FusionScission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FusionScissionMapper {

    FusionScissionMapper INSTANCE = Mappers.getMapper(FusionScissionMapper.class);

    @Mapping(target = "id", expression = "java(fusionScission.getId().toString())")
    @Mapping(target = "entreprise", source = "entreprise", qualifiedByName = "entrepriseFusci")
    FusionScissionRecord mapToFusci(FusionScission fusionScission);

    @Named("entrepriseFusci")
    default EntrepriseRecord mapEntrepriseForFusci(Entreprise entreprise) {
        return EntrepriseMapperUtils.mapToEntrepriseRecord(entreprise);
    }
}
