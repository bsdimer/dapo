package com.dapo.api.controller;

import com.dapo.common.jpa.model.RealEstateEntity;
import com.dapo.common.jpa.repository.RealEstateJpaRepository;
import com.dapo.common.jpa.service.RealEstateJpaService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dimomass on 21.12.18.
 */

@RestController
@RequestMapping("/realEstate")
public class RealEstateController {

    private RealEstateJpaService realEstateJpaService;

    @Autowired
    public RealEstateController(RealEstateJpaService realEstateJpaService) {
        this.realEstateJpaService = realEstateJpaService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAllPageable")
    public Page<RealEstateEntity> findAllPageable(Pageable pageable) {
        return realEstateJpaService.findAll(pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAll")
    public List<RealEstateEntity> findAll() {
        return (List<RealEstateEntity>) realEstateJpaService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public Iterable<RealEstateEntity> search(@QuerydslPredicate(root = RealEstateEntity.class) Predicate predicate) {
        return realEstateJpaService.findAll(predicate);
    }
}
