package com.dapo.common.jpa.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by dimomass on 10.03.19.
 */

@NoRepositoryBean
public interface CommonSearchRepository<E> extends PagingAndSortingRepository<E, Long>, QuerydslPredicateExecutor<E> {
}
