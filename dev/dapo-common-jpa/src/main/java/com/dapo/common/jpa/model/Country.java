package com.dapo.common.jpa.model;

import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by dimomass on 28.12.18.
 */

@Entity
public class Country extends AbstractEntity implements GeometryArea {

    private String name;
    @Column(columnDefinition = "geometry")
    private Polygon area;

    @Column(unique = true, nullable = false)
    @NotNull
    private String code;

    @OneToMany
    private Set<City> cities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
