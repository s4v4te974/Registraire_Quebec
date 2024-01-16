package com.registraire.main.models.dto;

import java.time.LocalDate;

public record ContinuationTransformationRecord(String id, String neq,
                                               String codTypChang, String codRegimJuri,
                                               String autrRegimJuri, String nomLoct,
                                               LocalDate datEfctvt, EntrepriseRecord entreprise) {
}
