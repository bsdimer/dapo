package com.dapo.common.jpa.model;

import com.dapo.common.json.views.PictureView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * Created by dimomass on 19.12.18.
 */

@Entity
public class PictureEntity extends AbstractEntity {

    @JsonView(PictureView.WithoutContent.class)
    private String checksum;
    @JsonView(PictureView.WithoutContent.class)
    private int size;
    @Lob
    @Column
    private byte[] source;
    @JsonView(PictureView.WithoutContent.class)
    @ManyToOne
    private RealEstateEntity target;
    @JsonView(PictureView.WithoutContent.class)
    private String extension;
    @JsonView(PictureView.WithoutContent.class)
    private String name;

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

    public byte[] getSource() {
        return source;
    }

    public void setSource(byte[] source) {
        this.source = source;
    }

    public RealEstateEntity getTarget() {
        return target;
    }

    public void setTarget(RealEstateEntity target) {
        this.target = target;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
