package com.dapo.common.domain;

import com.dapo.common.authentication.StandardUser;

/**
 * Created by dimomass on 18.12.18.
 */
public interface PropertyAnnouncement  {
    RealEstateEntity getProperty();

    AnnouncementType getType();

    Float getPrice();

    Currency getCurrency();

    Boolean isVip();

    StandardUser getOwner();
}
