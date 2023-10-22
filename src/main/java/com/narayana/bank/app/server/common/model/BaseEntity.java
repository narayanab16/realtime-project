package com.narayana.bank.app.server.common.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

public class BaseEntity implements Persistable<Serializable> {
    protected String id;
    @Transient
    protected boolean isNewObject;

    @Override
    @Transient
    public boolean isNew() {
        return this.isNewObject || id == null;
    }

    public String getId() {
        return this.id;
    }
}
