package com.dapo.common.jpa.repository;

import com.dapo.common.jpa.model.City;
import com.dapo.common.jpa.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by dimomass on 28.12.18.
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {
}
