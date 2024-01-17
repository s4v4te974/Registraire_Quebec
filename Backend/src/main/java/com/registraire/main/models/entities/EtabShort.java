package com.registraire.main.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "etab_short")
public class EtabShort {

    @Id
    @Column(name = "NOM_ETAB")
    private String nomEtab;

    @Column(name = "COMBINED_VALUES")
    private String combinedValues;

}
