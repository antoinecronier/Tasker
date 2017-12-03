package com.tactfactory.tasker.entity.base;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    protected Long id = 0L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
