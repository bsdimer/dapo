package com.dapo.common.jpa.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by dimomass on 15.02.19.
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment extends AbstractAuditableEntity implements Commentable {

    private String text;

    @OneToMany
    private Set<Comment> comments = new TreeSet<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Set<Comment> getComments() {
        return comments;
    }
}
