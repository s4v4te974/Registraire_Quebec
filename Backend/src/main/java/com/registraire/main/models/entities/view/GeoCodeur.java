package com.registraire.main.models.entities.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "etab_coordinates")
@IdClass(GeoCodeur.class)
public class GeoCodeur {

    @Id
    @Column(name = "NAME")
    private String nomEtab;

    @Id
    @Column(name = "ADRESSE")
    private String adresse;

    @Id
    @Column(name = "LOCALITE")
    private String localite;

    @Id
    @Column(name = "LOCALITE2")
    private String localite2;

    @Id
    @Column(name = "ZIPCODE")
    private String zipcode;

    @Column(name = "X_COORDINATE")
    private Float xCoordinate;

    @Column(name = "Y_COORDINATE")
    private Float yCoordinate;

    @Id
    @Column(name = "CODE")
    private Integer codDomVal;

    @Id
    @Column(name = "CODE_VALUE")
    private String valDomFran;

}
