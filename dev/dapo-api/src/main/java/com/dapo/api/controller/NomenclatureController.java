package com.dapo.api.controller;

import com.dapo.common.jpa.model.City;
import com.dapo.common.jpa.model.Country;
import com.dapo.common.jpa.model.RealEstateType;
import com.dapo.common.jpa.repository.CityRepository;
import com.dapo.common.jpa.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dimomass on 10.02.19.
 */

@RestController
@RequestMapping("/nm")
public class NomenclatureController {


    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/pt")
    public RealEstateType[] getPropertyTypes(){
        return RealEstateType.values();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cities")
    public Iterable<City> getCities(){
        return cityRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cities/findAllByCountry")
    public Iterable<City> findAllByCountry(@RequestParam(name = "country") Country country){
        return cityRepository.findAllByCountry(country);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/countries")
    public Iterable<Country> getCounttries(){
        return countryRepository.findAll();
    }
}
