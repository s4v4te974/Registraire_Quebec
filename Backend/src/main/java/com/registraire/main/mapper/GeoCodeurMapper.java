package com.registraire.main.mapper;

import com.registraire.main.models.dto.view.GeoCodeurRecord;
import com.registraire.main.models.entities.view.GeoCodeur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GeoCodeurMapper {

    GeoCodeurMapper INSTANCE = Mappers.getMapper(GeoCodeurMapper.class);

    @Mapping(target = "xCoordinate", source = "XCoordinate")
    @Mapping(target = "yCoordinate", source = "YCoordinate")
    @Mapping(target = "code", source = "codDomVal")
    @Mapping(target = "codeValue", source = "valDomFran")
    GeoCodeurRecord mapToGeoCodeurRecord(GeoCodeur codeur);

}
