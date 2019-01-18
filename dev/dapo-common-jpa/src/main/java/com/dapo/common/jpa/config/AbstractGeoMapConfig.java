package com.dapo.common.jpa.config;

import com.dapo.common.jpa.utils.geo.GeoUtils;
import org.springframework.context.annotation.Bean;

/**
 * Created by dimomass on 09.01.19.
 */

public abstract class AbstractGeoMapConfig {

    @Bean
    public GeoUtils getGeoUtils(){
        return new GeoUtils();
    }
}
