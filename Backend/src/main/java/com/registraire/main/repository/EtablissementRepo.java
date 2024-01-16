package com.registraire.main.repository;

import com.registraire.main.models.entities.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.registraire.main.repository.utils.QueryUtils.ETABS_QUERY;

@Repository
public interface EtablissementRepo extends JpaRepository<Etablissement, String> {

    @Query(value = ETABS_QUERY)
    List<Etablissement> findAllEtabs();

}
