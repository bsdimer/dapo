package com.dapo.common.jpa.model;

import com.dapo.common.domain.Picture;
import com.dapo.common.domain.RealEstateEntity;
import com.dapo.common.domain.RealEstateType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dimomass on 18.12.18.
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class RealEstateJpaEntity extends AbstractAuditableEntity implements RealEstateEntity {

    private Double latitude;
    private Double longitude;
    private RealEstateType type;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<PictureJpaEntity> pictures = new HashSet<>();

    @Override
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public RealEstateType getType() {
        return type;
    }

    public void setType(RealEstateType type) {
        this.type = type;
    }

    @Override
    public Set<Picture> getPictures() {
        return null;
    }
}
