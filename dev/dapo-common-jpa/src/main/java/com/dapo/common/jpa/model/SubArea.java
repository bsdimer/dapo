package com.dapo.common.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.*;

/**
 * Created by dimomass on 24.12.18.
 */

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"city_id" , "name"})})
public class SubArea extends AbstractEntity implements GeometryArea, NamedEntity {

    private String name;
    @Column(unique = true)
    private String code;
    @ManyToOne
    @JsonBackReference
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
        city.getSubAreas().add(this);
    }

    public Polygon getArea() {
        return area;
    }

    public void setArea(Polygon area) {
        this.area = area;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
