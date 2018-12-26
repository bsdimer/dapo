package com.dapo.common.domain;

import java.util.Set;

/**
 * Created by dimomass on 18.12.18.
 */
public interface RealEstateEntity extends GeoEntity {

    RealEstateType getType();

    void setType(RealEstateType realEstateType);

    Set<Picture> getPictures();

    String getDescription();

    Float getEnergyEfficiency();

    String getMunicipality();

    String getCity();

    String getNeighborhood();

    String getSubarea();

    ConstructionType getConstructionType();

    Byte getFloorCount();

    Byte getFloor();
}
