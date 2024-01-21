package com.registraire.main.repository;

import com.registraire.main.models.entities.basic.FusionScission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FusionScissionRepo extends JpaRepository<FusionScission, String> {
}
