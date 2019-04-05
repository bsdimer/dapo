package com.dapo.common.jpa.repository;

import com.dapo.common.jpa.model.District;
import com.dapo.common.jpa.model.Municipality;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by dimomass on 19.12.18.
 */

public interface MunicipalityRepository extends PagingAndSortingRepository<Municipality, Long>{
}
