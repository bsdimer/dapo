package com.dapo.common.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dimomass on 24.12.18.
 */

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"city_id", "name"})})
public class Municipality extends AbstractEntity implements GeometryArea, NamedEntity {

    private String name;

    @Column(columnDefinition = "geometry")
    private Polygon area;

    @ManyToOne
    @JsonBackReference
    private City city;

    @OneToMany
    @JsonManagedReference
    private List<Neighborhood> neighborhoods;

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
        city.getMunicipalityList().add(this);
    }

    @Override
    public Polygon getArea() {
        return area;
    }

    @Override
    public void setArea(Polygon area) {
        this.area = area;
    }

    public List<Neighborhood> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(List<Neighborhood> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }
}
