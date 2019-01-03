package com.dapo.common.jpa.repository;

import com.dapo.common.jpa.model.RealEstateEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by dimomass on 19.12.18.
 */

public interface RealEstateJpaRepository extends PagingAndSortingRepository<RealEstateEntity, Long>{
}
