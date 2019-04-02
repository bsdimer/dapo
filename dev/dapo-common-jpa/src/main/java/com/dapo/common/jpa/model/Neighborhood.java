package com.dapo.common.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.*;

/**
 * Created by dimomass on 24.12.18.
 */

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"city_id" , "name"})})
public class Neighborhood extends AbstractEntity implements GeometryArea, NamedEntity {

    private String name;

    @Column(columnDefinition = "geometry")
    private Polygon area;

    @ManyToOne
    @JsonBackReference
    private City city;

    @ManyToOne
    @JsonBackReference
    private Municipality municipality;

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

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }
}
