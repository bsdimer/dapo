package com.dapo.auth.model;

import javax.persistence.*;

/**
 * Created by dimomass on 09.02.19.
 */

@Entity
public class UserProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String key;

    @Column
    private String value;

    @ManyToOne
    private UserEntity userEntity;

    public Long getId() {
        return id;
    }
}
