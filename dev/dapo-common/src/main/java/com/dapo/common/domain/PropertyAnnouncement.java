package com.dapo.common.domain;

import com.dapo.common.authentication.StandardUser;

import java.math.BigDecimal;

/**
 * Created by dimomass on 18.12.18.
 */
public interface PropertyAnnouncement  {

    AnnouncementType getAnnouncementType();

    BigDecimal getPrice();

    BigDecimal getPricePerM2();

    Currency getCurrency();

    Boolean getVip();
}
