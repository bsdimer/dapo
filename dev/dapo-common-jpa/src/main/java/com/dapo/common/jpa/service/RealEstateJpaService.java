package com.dapo.common.jpa.service;

import com.dapo.common.jpa.model.RealEstateEntity;
import com.dapo.common.jpa.repository.RealEstateJpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by dimomass on 06.02.19.
 */

@Service
public class RealEstateJpaService extends CommonSearchService<RealEstateJpaRepository, RealEstateEntity> {

    public RealEstateJpaService(RealEstateJpaRepository repository) {
        super(repository);
    }
}
