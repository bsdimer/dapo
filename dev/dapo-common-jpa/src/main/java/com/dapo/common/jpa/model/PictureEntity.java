package com.dapo.common.jpa.model;

import com.dapo.common.domain.Picture;

import javax.persistence.Entity;

/**
 * Created by dimomass on 19.12.18.
 */

@Entity
public class PictureEntity extends AbstractEntity implements Picture {

    @Override
    public String getUri() {
        return null;
    }

    @Override
    public String getChecksum() {
        return null;
    }

    @Override
    public String getSize() {
        return null;
    }
}
