package com.registraire.main.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(CodeEtab.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "code_etab")
public class CodeEtab {

    @Id
    @Column(name = "COD_DOM_VAL")
    private Integer codDomVal;

    @Column(name = "VAL_DOM_FRAN")
    private String valDomFran;
}
