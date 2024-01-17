package com.registraire.main.repository.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QueryUtils {
    
    public static final String FIND_BY_NAME_AND_CODE_QUERY = "SELECT u FROM Etablissement u " +
            "WHERE u.codActEcon = :code AND u.nomEtab = :nomEtab " +
            "OR u.codActEcon2 = :code AND u.nomEtab = :nomEtab";

}
