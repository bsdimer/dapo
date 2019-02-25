package com.dapo.common.jpa.repository;

import com.dapo.common.jpa.model.City;
import com.dapo.common.jpa.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by dimomass on 15.02.19.
 */
public interface CommentRespository extends PagingAndSortingRepository<Comment, Long> {
}
