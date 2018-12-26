package com.dapo.common.jpa.model;

import javax.persistence.*;

/**
 * Created by dimomass on 24.12.18.
 */

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"city_id" , "name"})})
public class SubArea extends AbstractEntity {

    private String name;

    @ManyToOne
    private City city;

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
}
