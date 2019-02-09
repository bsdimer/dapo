package com.dapo.common.jpa.model;

import com.vividsolutions.jts.geom.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dimomass on 24.12.18.
 */

@Entity
public class City extends AbstractEntity implements GeometryArea, NamedEntity {

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Country country;

    @Column(columnDefinition = "geometry")
    private Polygon area;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Municipality> municipalityList = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Neighborhood> neighborhoods = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<SubArea> subAreas = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Municipality> getMunicipalityList() {
        return municipalityList;
    }

    public void setMunicipalityList(Set<Municipality> municipalityList) {
        this.municipalityList = municipalityList;
    }

    public Set<Neighborhood> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(Set<Neighborhood> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }

    public Set<SubArea> getSubAreas() {
        return subAreas;
    }

    public void setSubAreas(Set<SubArea> subAreas) {
        this.subAreas = subAreas;
    }

    public Polygon getArea() {
        return area;
    }

    public void setArea(Polygon area) {
        this.area = area;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
