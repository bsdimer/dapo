package com.dapo.api;

import com.dapo.common.domain.RealEstateEntity;
import com.dapo.common.jpa.model.RealEstateJpaEntity;
import com.dapo.common.jpa.repository.RealEstateJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * Created by dimomass on 21.12.18.
 */

@Component
public class TestComponent {


    @Autowired
    RealEstateJpaRepository realEstateJpaRepository;

    @PostConstruct
    public void contextLoads() throws Exception {
        RealEstateJpaEntity realEstateJpaEntity = new RealEstateJpaEntity();
        realEstateJpaEntity.setLatitude(12.32324223d);
        realEstateJpaEntity.setLongitude(123.23123123d);
        realEstateJpaRepository.save(realEstateJpaEntity);
    }
}
