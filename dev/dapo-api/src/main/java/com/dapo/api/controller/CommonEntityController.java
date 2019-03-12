package com.dapo.api.controller;

import com.dapo.common.jpa.model.RealEstateEntity;
import com.dapo.common.jpa.service.RealEstateJpaService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dimomass on 21.12.18.
 */

public class CommonEntityController<S > {

    private RealEstateJpaService realEstateJpaService;

    @Autowired
    public CommonEntityController(RealEstateJpaService realEstateJpaService) {
        this.realEstateJpaService = realEstateJpaService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public Iterable<RealEstateEntity> search(@QuerydslPredicate(root = RealEstateEntity.class) Predicate predicate) {
        return realEstateJpaService.findAll(predicate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search-pageable")
    @ResponseBody
    public Page<RealEstateEntity> searchPageable(@QuerydslPredicate(root = RealEstateEntity.class) Predicate predicate, Pageable pageable) {
        return realEstateJpaService.findAll(predicate, pageable);
    }
}
