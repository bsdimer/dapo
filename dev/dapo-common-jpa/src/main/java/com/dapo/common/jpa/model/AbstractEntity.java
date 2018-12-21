package com.dapo.common.jpa.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * Created by dimomass on 18.12.18.
 */

@MappedSuperclass
public abstract class AbstractEntity implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj ||
                this.id != null &&
                        obj instanceof AbstractEntity &&
                        obj.getClass().equals(getClass()) &&
                        this.id.equals(((AbstractEntity) obj).id);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

}
