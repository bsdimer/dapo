package com.dapo.common.domain;

import com.dapo.common.authentication.StandardUser;

import java.time.ZonedDateTime;

/**
 * Created by dimomass on 19.12.18.
 */
public interface Auditable {
    
    ZonedDateTime getCreated();
    
    StandardUser getCreatedBy();
    
    ZonedDateTime getModified();
    
    StandardUser modifiedBy();
    
}
