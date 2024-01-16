package com.registraire.main.repository;

import com.registraire.main.models.entities.Nom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NomRepo extends JpaRepository<Nom, String> {
}
