package com.dapo.api.controller;

import com.dapo.common.jpa.model.City;
import com.dapo.common.jpa.model.RealEstateType;
import com.dapo.common.jpa.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dimomass on 10.02.19.
 */

@RestController
@RequestMapping("/nm")
public class NomenclatureController {


    @Autowired
    CityRepository cityRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/pt")
    public RealEstateType[] getPropertyTypes(){
        return RealEstateType.values();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cities")
    public Iterable<City> getCities(){
        return cityRepository.findAll();
    }
}
