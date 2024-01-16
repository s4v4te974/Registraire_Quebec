package com.registraire.main.repository;

import com.registraire.main.models.entities.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtablissementRepo extends JpaRepository<Etablissement, String> {

    @Query("SELECT e.nomEtab FROM Etablissement e")
    List<String> retrieveEtabNames();
}
