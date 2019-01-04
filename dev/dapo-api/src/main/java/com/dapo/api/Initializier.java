package com.dapo.api;

import com.dapo.common.jpa.model.*;
import com.dapo.common.jpa.model.Currency;
import com.dapo.common.jpa.repository.CityRepository;
import com.dapo.common.jpa.repository.CountryRepository;
import com.dapo.common.jpa.repository.RealEstateJpaRepository;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

    int citiesCount = 100;
    int subareasCount = 100;
    int neighborhoodsCount = 100;
    int municipalitiesCount = 100;
    int realEstateCount = 1000;


    @PostConstruct
    public void contextLoads() throws Exception {
        Lorem lorem = LoremIpsum.getInstance();
        Country country = new Country();
        country.setName("България");
        country.setCode("BG");
        country.setArea(getRandomPolygon(41, 43, 25, 27));
        countryRepository.save(country);

        List<City> cities = new ArrayList<>();
        List<SubArea> subAreas = new ArrayList<>();
        List<Neighborhood> neighborhoods = new ArrayList<>();
        List<Municipality> municipalities = new ArrayList<>();


        // Generate cities
        for (int i = 0; i < citiesCount; i++) {
            City city = new City();
            cities.add(city);
            city.setCountry(country);
            city.setArea(getRandomPolygon(41, 43, 25, 27));
            city.setName(RandomStringUtils.randomAlphabetic(10));

            for (int j = 0; j < subareasCount; j++) {
                SubArea subArea = new SubArea();
                subArea.setName(RandomStringUtils.randomAlphabetic(10));
                subArea.setArea(getRandomPolygon(41, 43, 25, 27));
                subArea.setCity(city);
                subAreas.add(subArea);
            }

            for (int j = 0; j < neighborhoodsCount; j++) {
                Neighborhood neighborhood = new Neighborhood();
                neighborhood.setName(RandomStringUtils.randomAlphabetic(10));
                neighborhood.setArea(getRandomPolygon(41, 43, 25, 27));
                neighborhood.setCity(city);
                neighborhoods.add(neighborhood);
            }

            for (int j = 0; j < municipalitiesCount; j++) {
                Municipality municipality = new Municipality();
                municipality.setName(RandomStringUtils.randomAlphabetic(10));
                municipality.setArea(getRandomPolygon(41, 43, 25, 27));
                municipality.setCity(city);
                municipalities.add(municipality);
            }
            cityRepository.save(city);
        }

        for (int i = 0; i < realEstateCount; i++) {
            RealEstateEntity realEstateEntity = new RealEstateEntity();
            City city = cities.get(RandomUtils.nextInt(0, cities.size()));
            SubArea subArea = subAreas.get(RandomUtils.nextInt(0, subAreas.size()));
            Municipality municipality = municipalities.get(RandomUtils.nextInt(0, municipalities.size()));
            Neighborhood neighborhood = neighborhoods.get(RandomUtils.nextInt(0, neighborhoods.size()));
            realEstateEntity.setCity(city.getName());
            realEstateEntity.setSubarea(subArea.getName());
            realEstateEntity.setNeighborhood(neighborhood.getName());
            realEstateEntity.setMunicipality(municipality.getName());
            realEstateEntity.setType(RealEstateType.values()[RandomUtils.nextInt(0, RealEstateType.values().length)]);
            realEstateEntity.setAnnouncementType(AnnouncementType.values()[RandomUtils.nextInt(0, AnnouncementType.values().length)]);
            realEstateEntity.setConstructionType(ConstructionType.values()[RandomUtils.nextInt(0, ConstructionType.values().length)]);
            realEstateEntity.setCurrency(Currency.EUR);
            realEstateEntity.setPrice(new BigDecimal(RandomUtils.nextDouble(10000, 10000000)));
            realEstateEntity.setSize(RandomUtils.nextInt(50, 700));
            realEstateEntity.setPoint(getPoint(41, 43, 25, 27));
            realEstateEntity.setDescription(lorem.getWords(100, 200));
            realEstateJpaRepository.save(realEstateEntity);
        }

        /*RealEstateEntity realEstateEntity = new RealEstateEntity();

        realEstateEntity.setCity("Sofia");
        realEstateEntity.setNeighborhood("Mladost 1");
        realEstateEntity.setSubarea("Arsenalski");
        Point point = (Point) wktToGeometry("POINT (23.43312 45.232332)");
        realEstateEntity.setPoint(point);
        realEstateJpaRepository.save(realEstateEntity);

        City city = new City();
        city.setName("Sofia");

        Polygon polygon = (Polygon) wktToGeometry("POLYGON ((0 0, 0 5, 5 5, 5 0, 0 0))");
        city.setArea(polygon);
        cityRepository.save(city);*/
    }

    private Polygon getRandomPolygon(double latmin, double latmax, double longmin, double longmax) throws ParseException {
        Double startx = RandomUtils.nextDouble(latmin, latmax);
        Double starty = RandomUtils.nextDouble(longmin, longmax);
        return (Polygon) wktToGeometry(String.format("POLYGON ((%s %s, %s %s, %s %s, %s %s))",
                startx, starty,
                RandomUtils.nextDouble(startx, startx+0.1), RandomUtils.nextDouble(starty, starty+0.1),
                RandomUtils.nextDouble(startx, startx+0.1), RandomUtils.nextDouble(starty, starty+0.1),
                startx, starty
        ));
    }

    private Point getPoint(double minx, double maxx, double miny, double maxy) throws ParseException {
        Double x = RandomUtils.nextDouble(minx, maxx);
        Double y = RandomUtils.nextDouble(miny, maxy);
        return (Point) wktToGeometry(String.format("POINT (%s %s)", x, y));
    }

    private Geometry wktToGeometry(String wellKnownText)
            throws ParseException {

        return new WKTReader().read(wellKnownText);
    }
}
