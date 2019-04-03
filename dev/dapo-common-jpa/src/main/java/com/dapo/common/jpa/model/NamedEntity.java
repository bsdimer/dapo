package com.dapo.common.jpa.model;

/**
 * Created by dimomass on 09.02.19.
 */
public interface NamedEntity extends Identifiable {
    String getName();

    String getCode();
}
