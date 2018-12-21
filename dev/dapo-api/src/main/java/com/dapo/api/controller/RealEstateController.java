package com.dapo.api.controller;

import com.dapo.common.jpa.model.RealEstateJpaEntity;
import com.dapo.common.jpa.repository.RealEstateJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dimomass on 21.12.18.
 */

@RestController
@RequestMapping("/realEstate")
public class RealEstateController {

    private RealEstateJpaRepository realEstateJpaRepository;

    @Autowired
    public RealEstateController(RealEstateJpaRepository realEstateJpaRepository) {
        this.realEstateJpaRepository = realEstateJpaRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<RealEstateJpaEntity> findAll(Pageable pageable) {
        return realEstateJpaRepository.findAll(pageable);
    }
}
