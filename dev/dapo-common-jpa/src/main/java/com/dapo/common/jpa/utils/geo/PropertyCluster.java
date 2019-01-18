package com.dapo.common.jpa.utils.geo;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;

/**
 * Created by dimomass on 06.01.19.
 */
public class PropertyCluster {

    private long count;
    private Point topLeft;
    private Point bottomRight;
    private Point center;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    /*public Point getCenter() {
        try {
            return GeoUtils.getPoint((bottomRight.getX() - topLeft.getX())/2, (bottomRight.getY() - topLeft.getY())/2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public Point getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }
}
