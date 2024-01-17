package com.registraire.main.repository;

import com.registraire.main.models.entities.EtabShort;
import com.registraire.main.repository.utils.ReadOnlyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtabShortRepo extends ReadOnlyRepository<EtabShort, Integer> {
}
