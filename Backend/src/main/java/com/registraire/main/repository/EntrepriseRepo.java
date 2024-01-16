package com.registraire.main.repository;

import com.registraire.main.models.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepo extends JpaRepository<Entreprise, String> {
}
