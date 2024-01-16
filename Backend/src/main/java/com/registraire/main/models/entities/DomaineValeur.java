package com.registraire.main.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "domainevaleur")
public class DomaineValeur {
    @Id
    @Column(name = "COD_DOM_VAL")
    private int codDomVal;

    @Column(name = "VAL_DOM_FRAN")
    private String valDomFran;

}
