package com.dapo.common.jpa.model;

import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.*;

/**
 * Created by dimomass on 24.12.18.
 */

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"city_id" , "name"})})
public class SubArea extends AbstractEntity implements GeometryArea {

    private String name;
    @ManyToOne
    private City city;
    @Column(columnDefinition = "geometry")
    private Polygon area;

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
    }

    public Polygon getArea() {
        return area;
    }

    public void setArea(Polygon area) {
        this.area = area;
    }
}