package com.dapo.common.jpa.utils.geo;

import com.dapo.common.jpa.model.GeometryPoint;
import com.dapo.common.jpa.model.RealEstateEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimomass on 06.01.19.
 */
public class GeoItemsCollection {

    private List<PropertyCluster> propertyClusters = new ArrayList<>();
    private List<GeometryPoint> properties = new ArrayList<>();
    private int count;

    public List<PropertyCluster> getPropertyClusters() {
        return propertyClusters;
    }

    public void setPropertyClusters(List<PropertyCluster> propertyClusters) {
        this.propertyClusters = propertyClusters;
    }

    public List<GeometryPoint> getProperties() {
        return properties;
    }

    public void setProperties(List<GeometryPoint> properties) {
        this.properties = properties;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
