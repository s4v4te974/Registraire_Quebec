package com.registraire.main.mapper;

import com.registraire.main.models.dto.DomaineValeurRecord;
import com.registraire.main.models.entities.DomaineValeur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomaineValeurMapper {

    DomaineValeurMapper INSTANCE = Mappers.getMapper(DomaineValeurMapper.class);

    DomaineValeurRecord mapToDomaineRecord(DomaineValeur dm);
}
