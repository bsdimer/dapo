package com.dapo.common.jpa.model;

import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dimomass on 18.12.18.
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class RealEstateJpaEntity extends AbstractAuditableEntity implements PropertyAnnouncement, GeometryPoint {

    private RealEstateType type;
    @Column(length = 3000)
    private String description;
    private Float energyEfficiency;
    private String city;
    private String municipality;
    private String neighborhood;
    private String subarea;
    private ConstructionType constructionType;
    private Byte floorCount;
    private Byte floor;
    private AnnouncementType announcementType;
    private BigDecimal price;
    private BigDecimal pricePerM2;
    private Currency currency;
    private Boolean vip;
    @Column(columnDefinition = "geometry")
    private Point point;
    @OneToMany
    private Set<ExtraEntity> extras;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<PictureEntity> pictures = new HashSet<>();

    public RealEstateType getType() {
        return type;
    }

    public void setType(RealEstateType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getEnergyEfficiency() {
        return energyEfficiency;
    }

    public void setEnergyEfficiency(Float energyEfficiency) {
        this.energyEfficiency = energyEfficiency;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getSubarea() {
        return subarea;
    }

    public void setSubarea(String subarea) {
        this.subarea = subarea;
    }

    public ConstructionType getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(ConstructionType constructionType) {
        this.constructionType = constructionType;
    }

    public Byte getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(Byte floorCount) {
        this.floorCount = floorCount;
    }

    public Byte getFloor() {
        return floor;
    }

    public void setFloor(Byte floor) {
        this.floor = floor;
    }

    @Override
    public AnnouncementType getAnnouncementType() {
        return announcementType;
    }

    public void setAnnouncementType(AnnouncementType announcementType) {
        this.announcementType = announcementType;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getPricePerM2() {
        return pricePerM2;
    }

    public void setPricePerM2(BigDecimal pricePerM2) {
        this.pricePerM2 = pricePerM2;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public Set<ExtraEntity> getExtras() {
        return extras;
    }

    public void setExtras(Set<ExtraEntity> extras) {
        this.extras = extras;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}