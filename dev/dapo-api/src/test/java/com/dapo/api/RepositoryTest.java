package com.dapo.api;

import com.dapo.common.jpa.model.RealEstateJpaEntity;
import com.dapo.common.jpa.repository.RealEstateJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dimomass on 21.12.18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    RealEstateJpaRepository realEstateJpaRepository;

    @Test
    public void contextLoads() throws Exception {
        RealEstateJpaEntity realEstateJpaEntity = new RealEstateJpaEntity();
        realEstateJpaRepository.save(realEstateJpaEntity);
    }


}
