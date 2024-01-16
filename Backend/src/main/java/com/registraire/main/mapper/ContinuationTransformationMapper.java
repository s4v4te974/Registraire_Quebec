package com.registraire.main.mapper;

import com.registraire.main.mapper.utils.EntrepriseMapperUtils;
import com.registraire.main.models.dto.ContinuationTransformationRecord;
import com.registraire.main.models.dto.EntrepriseRecord;
import com.registraire.main.models.entities.ContinuationTransformation;
import com.registraire.main.models.entities.Entreprise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContinuationTransformationMapper {

    ContinuationTransformationMapper INSTANCE = Mappers.getMapper(ContinuationTransformationMapper.class);

    @Mapping(target = "id", expression = "java(continuationTransformation.getId().toString())")
    @Mapping(target = "entreprise", source = "entreprise", qualifiedByName = "entrepriseContiTransfo")
    ContinuationTransformationRecord mapToContiTransfo(ContinuationTransformation continuationTransformation);

    @Named("entrepriseContiTransfo")
    default EntrepriseRecord mapEntrepriseConti(Entreprise entreprise) {
        return EntrepriseMapperUtils.mapToEntrepriseRecord(entreprise);
    }
}
