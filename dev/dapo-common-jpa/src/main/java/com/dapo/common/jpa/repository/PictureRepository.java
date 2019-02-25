package com.dapo.common.jpa.repository;

import com.dapo.common.jpa.model.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dimomass on 27.12.18.
 */
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
}
