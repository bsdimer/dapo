package com.dapo.common.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimomass on 24.12.18.
 */

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"country_id", "code"})})
public class Municipality extends AbstractEntity implements GeometryArea, NamedEntity {

    @NotBlank
    private String name;

    @Column(unique = true)
    private String code;

    @Column(columnDefinition = "geometry")
    private Polygon area;

    @ManyToOne
    @JsonBackReference
    private Country country;

    @ManyToOne
    @JsonBackReference
    private District district;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<City> cities = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Polygon getArea() {
        return area;
    }

    @Override
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

    public List<City> getCities() {
        return cities;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
