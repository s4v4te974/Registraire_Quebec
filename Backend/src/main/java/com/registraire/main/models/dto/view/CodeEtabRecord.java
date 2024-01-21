package com.registraire.main.models.dto.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeEtabRecord {
    private Integer codDomVal;
    private String valDomFran;
}
