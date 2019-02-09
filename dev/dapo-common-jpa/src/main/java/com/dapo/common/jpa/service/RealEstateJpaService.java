package com.dapo.common.jpa.service;

import com.dapo.common.jpa.model.RealEstateEntity;
import com.dapo.common.jpa.repository.RealEstateJpaRepository;
import com.dapo.common.service.RealEstateService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Iterator;

/**
 * Created by dimomass on 06.02.19.
 */
public class RealEstateJpaService implements RealEstateService {

    private RealEstateJpaRepository repository;

    public RealEstateJpaService(RealEstateJpaRepository repository) {
        this.repository = repository;
    }

    public Page<RealEstateEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Iterable<RealEstateEntity> findAll() {
        return repository.findAll();
    }

    public Iterable<RealEstateEntity> findAll(Predicate predicate) {
        return repository.findAll(predicate);
    }
}
