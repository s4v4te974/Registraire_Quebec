package com.registraire.main.repository;

import com.registraire.main.models.entities.basic.ContinuationTransformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinuationTransformationRepo extends JpaRepository<ContinuationTransformation, String> {
}
