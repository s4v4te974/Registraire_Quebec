package com.registraire.main.models.dto.basic;

import java.time.LocalDate;

public record NomRecord(String id, String neq, String nomAssuj, String nomAssujLangEtrng,
                        String statNom, String typNomAssuj, LocalDate datInitNomAssuj,
                        LocalDate datFinNomAssuj, EntrepriseRecord entreprise) {
}
