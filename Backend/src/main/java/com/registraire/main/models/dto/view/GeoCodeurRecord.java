package com.registraire.main.models.dto.view;

public record GeoCodeurRecord(String nomEtab, String adresse, String localite,
                              String localite2, String zipcode, float xCoordinate,
                              float yCoordinate, Integer code, String codeValue) {
}
