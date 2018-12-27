package com.dapo.common.jpa.model;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

/**
 * Created by dimomass on 27.12.18.
 */
public interface GeometryPoint {

    Point getPoint();

    void setPoint(Point point);
}
