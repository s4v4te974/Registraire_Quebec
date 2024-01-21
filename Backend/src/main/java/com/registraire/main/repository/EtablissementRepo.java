package com.registraire.main.repository;

import com.registraire.main.models.entities.basic.Etablissement;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.registraire.main.repository.utils.QueryUtils.FIND_BY_NAME_AND_CODE_QUERY;

@Repository
public interface EtablissementRepo extends JpaRepository<Etablissement, String> {

    List<Etablissement> findByNomEtab(@NotNull String nom);

    List<Etablissement> findByCodActEconOrCodActEcon2(int firstCode, int secondCode);

    @Query(FIND_BY_NAME_AND_CODE_QUERY)
    List<Etablissement> findByNamesAndCodes(@Param("nomEtab") String nom, @Param("code") int code);

}
