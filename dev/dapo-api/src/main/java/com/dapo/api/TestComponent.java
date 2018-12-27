package com.dapo.api;

import com.dapo.common.jpa.model.City;
import com.dapo.common.jpa.model.RealEstateJpaEntity;
import com.dapo.common.jpa.repository.CityRepository;
import com.dapo.common.jpa.repository.RealEstateJpaRepository;
import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * Created by dimomass on 21.12.18.
 */

@Component
public class TestComponent {


    @Autowired
    RealEstateJpaRepository realEstateJpaRepository;

    @Autowired
    CityRepository cityRepository;

    @PostConstruct
    public void contextLoads() throws Exception {
        RealEstateJpaEntity realEstateJpaEntity = new RealEstateJpaEntity();

        realEstateJpaEntity.setCity("Sofia");
        realEstateJpaEntity.setNeighborhood("Mladost 1");
        realEstateJpaEntity.setSubarea("Arsenalski");
        Point point = (Point) wktToGeometry("POINT (23.43312 45.232332)");
        realEstateJpaEntity.setPoint(point);
        realEstateJpaRepository.save(realEstateJpaEntity);


        City city = new City();
        city.setName("Sofia");

        Polygon polygon = (Polygon) wktToGeometry("POLYGON ((0 0, 0 5, 5 5, 5 0, 0 0))");
        city.setArea(polygon);
        cityRepository.save(city);

    }

    public Geometry wktToGeometry(String wellKnownText)
            throws ParseException {

        return new WKTReader().read(wellKnownText);
    }
}
