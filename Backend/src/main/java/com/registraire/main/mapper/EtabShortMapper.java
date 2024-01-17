package com.registraire.main.mapper;

import com.registraire.main.models.dto.EtabShortRecord;
import com.registraire.main.models.entities.EtabShort;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EtabShortMapper {

    EtabShortMapper INSTANCE = Mappers.getMapper(EtabShortMapper.class);

    EtabShortRecord mapToEtabShort(EtabShort eS);

}
