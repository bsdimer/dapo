package com.dapo.common.jpa.model;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.dapo.common.jpa.serializiers.NamedEntitySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by dimomass on 18.12.18.
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class RealEstateEntity extends AbstractAuditableEntity implements PropertyAnnouncement, GeometryPoint, Commentable {

    @NotNull
    @OneToOne
    @JsonSerialize(using = NamedEntitySerializer.class)
    private City city;
    @OneToOne
    @JsonSerialize(using = NamedEntitySerializer.class)
    private Municipality municipality;
    @OneToOne
    @JsonSerialize(using = NamedEntitySerializer.class)
    private Neighborhood neighborhood;
    @OneToOne
    @JsonSerialize(using = NamedEntitySerializer.class)
    private SubArea subarea;
    private RealEstateType type;
    private ConstructionType constructionType;
    private Byte floorCount;
    private Byte floor;
    private AnnouncementType announcementType;
    @Column(nullable = false)
    @NotNull
    private BigDecimal price;
    private BigDecimal pricePerM2;
    @Column(nullable = false)
    @NotNull
    private int size = 0;
    private Currency currency = Currency.EUR;
    private PriorityType priorityType = PriorityType.NORMAL;
    @Column(columnDefinition = "geometry")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private Point point;
    @OneToMany
    private Set<ExtraEntity> extras;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<PictureEntity> pictures = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments = new TreeSet<>();
    @Column(length = 3000)
    private String description;
    @ElementCollection
    private List<String> tags = new ArrayList<>();
    private Float energyEfficiency;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public SubArea getSubarea() {
        return subarea;
    }

    public void setSubarea(SubArea subarea) {
        this.subarea = subarea;
    }

    public void setPricePerM2(BigDecimal pricePerM2) {
        this.pricePerM2 = pricePerM2;
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
        if (this.getPrice() != null && this.size > 0)
            this.pricePerM2 = this.price.divide(BigDecimal.valueOf(this.size), 2);
    }

    @Override
    public BigDecimal getPricePerM2() {
        return pricePerM2;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getSize() {
        return size;
    }

        public void setSize(int size) {
        this.size = size;
        if (this.getPrice() != null && this.size > 0)
            this.pricePerM2 = this.price.divide(BigDecimal.valueOf(this.size), 2);
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    @Override
    public Set<Comment> getComments() {
        return comments;
    }
}
