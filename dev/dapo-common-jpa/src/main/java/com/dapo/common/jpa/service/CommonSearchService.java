package com.dapo.common.jpa.service;

import com.dapo.common.jpa.model.RealEstateEntity;
import com.dapo.common.jpa.repository.CommonSearchRepository;
import com.dapo.common.jpa.repository.RealEstateJpaRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by dimomass on 10.03.19.
 */
public abstract class CommonSearchService<R extends CommonSearchRepository, E> {

    protected R repository;

    public CommonSearchService(R repository) {
        this.repository = repository;
    }

    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Iterable<E> findAll() {
        return repository.findAll();
    }

    public Page<E> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    public Iterable<E> findAll(Predicate predicate) {
        return repository.findAll(predicate);
    }
}
