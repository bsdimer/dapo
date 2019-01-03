package com.dapo.common.jpa.model;

import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.*;

/**
 * Created by dimomass on 24.12.18.
 */

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"city_id" , "name"})})
public class Neighborhood extends AbstractEntity implements GeometryArea {

    private String name;
    @Column(columnDefinition = "geometry")
    private Polygon area;
    @ManyToOne
    private City city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
        city.getNeighborhoods().add(this);
    }

    @Override
    public Polygon getArea() {
        return area;
    }

    @Override
    public void setArea(Polygon area) {
        this.area = area;
    }
}
