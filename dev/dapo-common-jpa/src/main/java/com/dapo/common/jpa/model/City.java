package com.dapo.common.jpa.model;

import com.vividsolutions.jts.geom.*;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by dimomass on 24.12.18.
 */

@Entity
public class City extends AbstractEntity {

    @Column(unique = true)
    private String name;
    /*private Double latitude;
    private Double longitude;*/

    @Column(columnDefinition = "geometry")
    private Polygon area;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Municipality> municipalityList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Neighborhood> neighborhoods;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SubArea> subAreas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Municipality> getMunicipalityList() {
        return municipalityList;
    }

    public void setMunicipalityList(List<Municipality> municipalityList) {
        this.municipalityList = municipalityList;
    }

    public List<Neighborhood> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(List<Neighborhood> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }

    public List<SubArea> getSubAreas() {
        return subAreas;
    }

    public void setSubAreas(List<SubArea> subAreas) {
        this.subAreas = subAreas;
    }

    public Polygon getArea() {
        return area;
    }

    public void setArea(Polygon area) {
        this.area = area;
    }
}
