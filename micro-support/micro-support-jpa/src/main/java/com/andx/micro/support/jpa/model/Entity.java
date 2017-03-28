package com.andx.micro.support.jpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by andongxu on 16-6-7.
 */
@MappedSuperclass
public class Entity implements Serializable {

    @Column(nullable = false)
    private Timestamp createdTime;
    @Column(nullable = false)
    private Timestamp lastUpdateTime;

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdateTime = new Timestamp(new java.util.Date().getTime());
    }

    @PrePersist
    public void prePersist() {
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        createdTime = now;
        lastUpdateTime = now;
    }
}
