package com.dapo.common.jpa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by dimomass on 19.12.18.
 */

@Entity
public class PictureEntity extends AbstractEntity {

    private String uri;
    private String checksum;
    private int size;
    @ManyToOne
    private RealEstateJpaEntity target;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
