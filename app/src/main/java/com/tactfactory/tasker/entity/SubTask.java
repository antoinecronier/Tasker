package com.tactfactory.tasker.entity;

import com.tactfactory.tasker.entity.base.BaseEntity;

/** Sub-Task POJO. */
public class SubTask extends BaseEntity {

    /** Name of Sub-Task. */
    protected String name;

    /** Task owner. */
    protected Task owner;

    public SubTask(String name, Task task) {
        this.name = name;
        this.setOwner(task);
    }

    public String getName() {
        return name;
    }

    public SubTask setName(String name) {
        this.name = name;

        return this;
    }

    public Task getOwner() {
        return owner;
    }

    public SubTask setOwner(Task owner) {
        this.owner = owner;

        if (!owner.hasSubTask(this)) {
            owner.addSubTask(this);
        }

        return this;
    }
}
