package com.dapo.api;

import com.dapo.common.jpa.model.*;
import com.dapo.common.jpa.model.Currency;
import com.dapo.common.jpa.repository.*;
import com.dapo.common.utils.GeoUtils;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.vividsolutions.jts.io.ParseException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by dimomass on 21.12.18.
 */

@Component
@Profile("init")
public class Initializier {


    @Autowired
    RealEstateJpaRepository realEstateJpaRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CommentRespository commentRespository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    MunicipalityRepository municipalityRepository;


    private static final Logger logger = LoggerFactory.getLogger(Initializier.class);

    /*int citiesCount = 5;
    int subareasCount = 5;
    int neighborhoodsCount = 5;
    int municipalitiesCount = 5;
    int realEstateCount = 1000;*/

    @PostConstruct
    @Transactional
    public void initialize() throws ParseException {
        Country country = new Country();
        country.setName("Bulgaria");
        country.setCode("BG");
        country.setArea(GeoUtils.getRandomPolygon(41, 43, 25, 27));
        countryRepository.save(country);

        List<String> districtLines = readFile2Lines("/home/dimomass/dev/projects/dapo/dev/dapo-api/src/main/resources/import/district");
        List<String> municipalityLines = readFile2Lines("/home/dimomass/dev/projects/dapo/dev/dapo-api/src/main/resources/import/municipality");
        List<String> citiesLines = readFile2Lines("/home/dimomass/dev/projects/dapo/dev/dapo-api/src/main/resources/import/cities");

        Map<String, District> districtMap = new HashMap<>();
        Map<String, Municipality> municipalityMap = new HashMap<>();
        Map<String, City> cityMap = new HashMap<>();

        districtLines.forEach(districtLine -> {
            final String[] dParts = districtLine.split("\t");
            District district = new District();
            district.setCode(dParts[0]);
            district.setName(dParts[2]);
            district.setCountry(country);
            district = districtRepository.save(district);
            districtMap.put(district.getCode(), district);
        });

        municipalityLines.forEach(mLine -> {
            final String[] mlineParts = mLine.split("\t");
            Municipality municipality = new Municipality();
            municipality.setCode(mlineParts[0]);
            municipality.setName(mlineParts[2]);
            municipality.setCountry(country);
            District district = districtMap.get(municipality.getCode().substring(0, 3));
            municipality.setDistrict(district);
            municipalityMap.put(municipality.getCode(), municipality);
            municipality = municipalityRepository.save(municipality);
            district.getMunicipalities().add(municipality);
        });

        citiesLines.forEach(cLine -> {
            final String[] cityLineParts = cLine.split("\t");
            City city = new City();
            city.setCode(cityLineParts[0]);
            city.setType(CityType.values()[Integer.parseInt(cityLineParts[1])]);
            city.setName(cityLineParts[2]);
            city.setCountry(country);
            city = cityRepository.save(city);
            District district = districtMap.get(cityLineParts[3]);
            city.setDistrict(district);
            Municipality municipality = municipalityMap.get(cityLineParts[4]);
            city.setMunicipality(municipality);
            district.getCities().add(city);
            municipality.getCities().add(city);
            cityMap.put(city.getCode(), city);
        });

        /*cityMap.values().forEach(city -> {
            cityRepository.save(city);
        });*/

        /*municipalityMap.values().forEach(municipality -> {
            municipalityRepository.save(municipality);
        });*/

        districtMap.values().forEach(district -> {
            districtRepository.save(district);
        });




        /*citiesLines.forEach(cityLine -> {
            final String[] clineParts = cityLine.split("\t");
            City municipality = new City();
            municipality.setCode(mlineParts[0]);
            municipality.setName(mlineParts[2]);
            municipality = municipalityRepository.save(municipality);
            District district = districtMap.get(municipality.getCode().substring(0, 3);
            municipality.setDistrict(district);
            district.getMunicipalities().add(municipality);
            municipalityMap.put(municipality.getCode(), municipality);
            districtRepository.save(district);
        });*/
    }

    private List<String> readFile2Lines(String path) {
        BufferedReader reader;
        List<String> result = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                result.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            ;
        }
        return result;
    }


    /*@PostConstruct
    public void contextLoads() throws Exception {
        Lorem lorem = LoremIpsum.getInstance();
        Country country = new Country();
        country.setName("Bulgaria");
        country.setCode("BG");
        country.setArea(GeoUtils.getRandomPolygon(41, 43, 25, 27));
        countryRepository.save(country);

        List<City> cities = new ArrayList<>();
        List<District> districts = new ArrayList<>();
        List<Municipality> municipalities = new ArrayList<>();

        // Generate cities
        for (int i = 0; i < citiesCount; i++) {
            City city = new City();
            cities.add(city);
            city.setCountry(country);
            city.setArea(GeoUtils.getRandomPolygon(41, 43, 25, 27));
            city.setName(RandomStringUtils.randomAlphabetic(10));

            for (int j = 0; j < subareasCount; j++) {
                District district = new District();
                district.setName(RandomStringUtils.randomAlphabetic(10));
                district.setArea(GeoUtils.getRandomPolygon(41, 43, 25, 27));
                district.setCity(city);
                districts.add(district);
            }

            *//*for (int j = 0; j < neighborhoodsCount; j++) {
                Neighborhood neighborhood = new Neighborhood();
                neighborhood.setName(RandomStringUtils.randomAlphabetic(10));
                neighborhood.setArea(GeoUtils.getRandomPolygon(41, 43, 25, 27));
                neighborhood.setCity(city);
                neighborhoods.add(neighborhood);
            }*//*

            for (int j = 0; j < municipalitiesCount; j++) {
                Municipality municipality = new Municipality();
                municipality.setName(RandomStringUtils.randomAlphabetic(10));
                municipality.setArea(GeoUtils.getRandomPolygon(41, 43, 25, 27));
                municipality.setCity(city);
                municipalities.add(municipality);
            }

            cityRepository.save(city);
            logger.info(String.format("Saving CITY %s %s", i, city.getName()));
        }

        for (int i = 0; i < realEstateCount; i++) {
            RealEstateEntity realEstateEntity = new RealEstateEntity();
            City city = cities.get(RandomUtils.nextInt(0, cities.size()));
            District district = districts.get(RandomUtils.nextInt(0, districts.size()));
            Municipality municipality = municipalities.get(RandomUtils.nextInt(0, municipalities.size()));
            //Neighborhood neighborhood = neighborhoods.get(RandomUtils.nextInt(0, neighborhoods.size()));
            realEstateEntity.setCity(city);
            realEstateEntity.setDistrict(district);
            realEstateEntity.setMunicipality(municipality);
            realEstateEntity.setType(RealEstateType.values()[RandomUtils.nextInt(0, RealEstateType.values().length)]);
            realEstateEntity.setAnnouncementType(AnnouncementType.values()[RandomUtils.nextInt(0, AnnouncementType.values().length)]);
            realEstateEntity.setConstructionType(ConstructionType.values()[RandomUtils.nextInt(0, ConstructionType.values().length)]);
            realEstateEntity.setCurrency(Currency.EUR);
            realEstateEntity.setPrice(new BigDecimal(RandomUtils.nextDouble(10000, 10000000)));
            realEstateEntity.setSize(RandomUtils.nextInt(50, 700));
            realEstateEntity.setPoint(GeoUtils.getRandomPoint(41, 43, 25, 27));
            realEstateEntity.setDescription(lorem.getWords(100, 200));

            realEstateJpaRepository.save(realEstateEntity);

            Comment comment1 = new Comment();
            comment1.setText(RandomStringUtils.randomAlphabetic(50));
            Comment comment2 = new Comment();
            comment2.setText(RandomStringUtils.randomAlphabetic(50));
            commentRespository.save(comment1);
            commentRespository.save(comment2);
            comment1.getComments().add(comment2);
            realEstateEntity.getComments().add(comment1);
            logger.info(String.format("Saving RENTITY %s", i));
        }

        *//*RealEstateEntity realEstateEntity = new RealEstateEntity();

        realEstateEntity.setCity("Sofia");
        realEstateEntity.setNeighborhood("Mladost 1");
        realEstateEntity.setDistrict("Arsenalski");
        PointShapeFactory.Point point = (PointShapeFactory.Point) wktToGeometry("POINT (23.43312 45.232332)");
        realEstateEntity.setPoint(point);
        realEstateJpaRepository.save(realEstateEntity);

        City city = new City();
        city.setName("Sofia");

        Polygon polygon = (Polygon) wktToGeometry("POLYGON ((0 0, 0 5, 5 5, 5 0, 0 0))");
        city.setArea(polygon);
        cityRepository.save(city);*//*
    }*/
}
