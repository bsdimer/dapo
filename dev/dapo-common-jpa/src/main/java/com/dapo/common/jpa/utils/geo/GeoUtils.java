package com.dapo.common.jpa.utils.geo;

import com.dapo.common.jpa.model.GeometryPoint;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by dimomass on 06.01.19.
 */
public class GeoUtils {

    private int maxItemsInQuadrant = 4;
    private int qudrantStepIndex = 3;

    public GeoItemsCollection normalizeCollection(Collection<GeometryPoint> collection,
                                                  Double altitude,
                                                  int itemsPerQuadrant) throws ParseException {
        GeoItemsCollection geoItemsCollection = new GeoItemsCollection();
        GeometryPoint leftTop = (GeometryPoint) collection.toArray()[0];
        GeometryPoint rightBottom = (GeometryPoint) collection.toArray()[0];
        for (GeometryPoint geometryPoint : collection) {
            if (geometryPoint.getPoint().getX() < leftTop.getPoint().getX()
                    && geometryPoint.getPoint().getY() < leftTop.getPoint().getY()) leftTop = geometryPoint;

            if (geometryPoint.getPoint().getX() > rightBottom.getPoint().getX()
                    && geometryPoint.getPoint().getY() > rightBottom.getPoint().getY()) rightBottom = geometryPoint;
        }
        double deltaX = rightBottom.getPoint().getX() - leftTop.getPoint().getX();
        double deltaY = rightBottom.getPoint().getY() - leftTop.getPoint().getY();
        double stepX = deltaX / qudrantStepIndex;
        double stepY = deltaY / qudrantStepIndex;
        PropertyCluster propertyCluster = null;
        for (double x = leftTop.getPoint().getX(), z = 1; x <= rightBottom.getPoint().getX(); x = x + stepX, z++) {
            for (double y = leftTop.getPoint().getY(), v = 1; y <= rightBottom.getPoint().getY(); y = y + stepY, v++) {
                System.out.println(String.format("x:%s y:%s (%.0f,%.0f)", x, y, z, v));
                if (v < qudrantStepIndex) {
                    propertyCluster = new PropertyCluster();
                    geoItemsCollection.getPropertyClusters().add(propertyCluster);
                    propertyCluster.setTopLeft(getPoint(x,y));
                }
            }
        }

        return geoItemsCollection;
    }


    public static Polygon getRandomPolygon(double latmin, double latmax, double longmin, double longmax) throws ParseException {
        Double startx = RandomUtils.nextDouble(latmin, latmax);
        Double starty = RandomUtils.nextDouble(longmin, longmax);
        return (Polygon) wktToGeometry(String.format("POLYGON ((%s %s, %s %s, %s %s, %s %s))",
                startx, starty,
                RandomUtils.nextDouble(startx, startx + 0.1), RandomUtils.nextDouble(starty, starty + 0.1),
                RandomUtils.nextDouble(startx, startx + 0.1), RandomUtils.nextDouble(starty, starty + 0.1),
                startx, starty
        ));
    }

    public static Point getRandomPoint(double minx, double maxx, double miny, double maxy) throws ParseException {
        Double x = RandomUtils.nextDouble(minx, maxx);
        Double y = RandomUtils.nextDouble(miny, maxy);
        return (Point) wktToGeometry(String.format("POINT (%s %s)", x, y));
    }

    public static Point getPoint(double x, double y) throws ParseException {
        return (Point) wktToGeometry(String.format("POINT (%s %s)", x, y));
    }

    public static Geometry wktToGeometry(String wellKnownText)
            throws ParseException {
        return new WKTReader().read(wellKnownText);
    }
}
