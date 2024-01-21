package com.registraire.main.mapper;

import com.registraire.main.models.dto.view.CodeEtabRecord;
import com.registraire.main.models.entities.view.CodeEtab;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CodeEtabMapper {

    CodeEtabMapper INSTANCE = Mappers.getMapper(CodeEtabMapper.class);

    CodeEtabRecord mapToCodeEtabRecord(CodeEtab cE);
}
