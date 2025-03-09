package org.example.controller.model;

import org.example.persistence.model.BaseEntity;

public abstract class DTO {
    public BaseEntity toEntity() {
        return new BaseEntity();
    }
}
