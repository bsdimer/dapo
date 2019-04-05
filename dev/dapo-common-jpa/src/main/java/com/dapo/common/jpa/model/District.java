package com.dapo.common.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimomass on 24.12.18.
 */

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"country_id", "code"})})
public class District extends AbstractEntity implements GeometryArea, NamedEntity {

    private String name;

    @Column(unique = true)
    private String code;

    @Column(columnDefinition = "geometry")
    private Polygon area;

    @ManyToOne
    @JsonIgnore
    private Country country;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Municipality> municipalities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<City> cities = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Municipality> getMunicipalities() {
        return municipalities;
    }

    public List<City> getCities() {
        return cities;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
