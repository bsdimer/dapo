package com.dapo.common.jpa.repository;

import com.dapo.common.jpa.model.QRealEstateEntity;
import com.dapo.common.jpa.model.RealEstateEntity;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by dimomass on 19.12.18.
 */

public interface RealEstateJpaRepository extends PagingAndSortingRepository<RealEstateEntity, Long>,
        QuerydslPredicateExecutor<RealEstateEntity>{
}
