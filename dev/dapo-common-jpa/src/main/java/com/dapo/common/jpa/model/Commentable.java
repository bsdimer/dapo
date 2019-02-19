package com.dapo.common.jpa.model;

import com.dapo.common.jpa.exception.ComparableException;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletionException;

/**
 * Created by dimomass on 15.02.19.
 */
public interface Commentable extends Comparable<Comment>, Identifiable {
    Set<Comment> getComments();

    @Override
    default int compareTo(Comment o) {
        if (o.getId() == null || this.getId() == null) throw new ComparableException("Cannot compare ids with null value!");
        return o.getId().compareTo(this.getId());
    }
}
