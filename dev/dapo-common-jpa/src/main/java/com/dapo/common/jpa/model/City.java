package com.dapo.common.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vividsolutions.jts.geom.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dimomass on 24.12.18.
 */

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"country_id", "code"})})
public class City extends AbstractEntity implements GeometryArea, NamedEntity {

    @NotBlank
    private String name;

    @Column(unique = true)
    private String code;

    private CityType type;

    @ManyToOne
    @JsonIgnore
    private Country country;

    @Column(columnDefinition = "geometry")
    private Polygon area;

    @ManyToOne
    @JsonBackReference
    private Municipality municipality;

    @ManyToOne
    @JsonBackReference
    private District district;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public CityType getType() {
        return type;
    }

    public void setType(CityType type) {
        this.type = type;
    }
}
