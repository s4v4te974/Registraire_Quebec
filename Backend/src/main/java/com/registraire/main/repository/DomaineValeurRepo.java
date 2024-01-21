package com.registraire.main.repository;

import com.registraire.main.models.entities.basic.DomaineValeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomaineValeurRepo extends JpaRepository<DomaineValeur, Integer> {
}
