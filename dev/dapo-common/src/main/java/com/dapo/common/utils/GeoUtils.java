package com.dapo.common.utils;


import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.apache.commons.lang3.RandomUtils;

/**
 * Created by dimomass on 06.01.19.
 */
public class GeoUtils {


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
