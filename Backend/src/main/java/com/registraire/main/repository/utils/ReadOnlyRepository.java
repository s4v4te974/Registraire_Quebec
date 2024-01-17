package com.registraire.main.repository.utils;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface ReadOnlyRepository<T, iD> extends Repository<T, iD> {

    List<T> findAll();

}
