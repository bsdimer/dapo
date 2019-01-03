package com.dapo.common.jpa.repository;

import com.dapo.common.jpa.model.City;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by dimomass on 19.12.18.
 */

public interface CityRepository extends PagingAndSortingRepository<City, Long>{
}
