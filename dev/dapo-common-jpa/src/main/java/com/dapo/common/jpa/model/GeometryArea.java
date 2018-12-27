package com.dapo.common.jpa.model;

import com.vividsolutions.jts.geom.Polygon;

/**
 * Created by dimomass on 27.12.18.
 */
public interface GeometryArea {

    Polygon getArea();

    void setArea(Polygon area);
}
