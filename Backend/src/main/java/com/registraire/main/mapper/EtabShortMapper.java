package com.registraire.main.mapper;

import com.registraire.main.models.dto.view.EtabShortRecord;
import com.registraire.main.models.entities.view.EtabShort;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EtabShortMapper {

    EtabShortMapper INSTANCE = Mappers.getMapper(EtabShortMapper.class);

    EtabShortRecord mapToEtabShort(EtabShort eS);

}
