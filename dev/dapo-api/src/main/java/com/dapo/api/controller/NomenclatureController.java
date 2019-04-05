package com.dapo.api.controller;

import com.dapo.common.jpa.model.*;
import com.dapo.common.jpa.repository.CityRepository;
import com.dapo.common.jpa.repository.CountryRepository;
import com.dapo.common.jpa.repository.DistrictRepository;
import com.dapo.common.jpa.repository.MunicipalityRepository;
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


    private final CityRepository cityRepository;

    private final DistrictRepository districtRepository;

    private final MunicipalityRepository municipalityRepository;

    private final CountryRepository countryRepository;

    @Autowired
    public NomenclatureController(DistrictRepository districtRepository,
                                  MunicipalityRepository municipalityRepository,
                                  CountryRepository countryRepository,
                                  CityRepository cityRepository) {
        this.districtRepository = districtRepository;
        this.municipalityRepository = municipalityRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pt")
    public RealEstateType[] getPropertyTypes(){
        return RealEstateType.values();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/city")
    public Iterable<City> getCities(){
        return cityRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/district")
    public Iterable<District> getDistricts(){
        return districtRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/district/findAllByCountry")
    public Iterable<District> findAllDistrictsByCountry(@RequestParam(name = "country") Country country){
        return districtRepository.findAllByCountry(country);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/municipality")
    public Iterable<Municipality> getMunicipalities(){
        return municipalityRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/city/findAllByCountry")
    public Iterable<City> findAllCitiesByCountry(@RequestParam(name = "country") Country country){
        return cityRepository.findAllByCountry(country);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/country")
    public Iterable<Country> getCounttries(){
        return countryRepository.findAll();
    }
}
