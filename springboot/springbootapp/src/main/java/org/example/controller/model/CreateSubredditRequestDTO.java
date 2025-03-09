package org.example.controller.model;

import lombok.Data;
import org.example.persistence.model.BaseEntity;
import org.example.persistence.model.PostEntity;
import org.example.persistence.model.SubredditEntity;

@Data
public class CreateSubredditRequestDTO extends DTO{
    String name;
    String description;

    @Override
    public BaseEntity toEntity() {
        SubredditEntity subredditEntity = new SubredditEntity();
        subredditEntity.setName(name);
        subredditEntity.setDesctription(description);
        return subredditEntity;
    }
}