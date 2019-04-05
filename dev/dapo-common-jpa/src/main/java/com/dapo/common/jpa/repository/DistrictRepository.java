package com.dapo.common.jpa.repository;

import com.dapo.common.jpa.model.Country;
import com.dapo.common.jpa.model.District;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by dimomass on 19.12.18.
 */

public interface DistrictRepository extends PagingAndSortingRepository<District, Long>{
    Iterable<District> findAllByCountry(Country country);
}
