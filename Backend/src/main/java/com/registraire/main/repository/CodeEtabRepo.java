package com.registraire.main.repository;

import com.registraire.main.models.entities.CodeEtab;
import com.registraire.main.repository.utils.ReadOnlyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeEtabRepo extends ReadOnlyRepository<CodeEtab, Integer> {


}
