package org.example.controller.model;

import lombok.Data;
import org.example.persistence.model.BaseEntity;
import org.example.persistence.model.PostEntity;
import org.example.persistence.model.SubredditEntity;

@Data
public class JoinSubredditRequestDTO extends DTO{
    Long id;

    @Override
    public BaseEntity toEntity() {
        SubredditEntity subredditEntity = new SubredditEntity();
        subredditEntity.setId(id);
        return subredditEntity;
    }
}
