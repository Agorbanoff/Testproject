package org.example.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.persistence.model.BaseEntity;
import org.example.persistence.model.SubredditEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubredditRequestDTO extends DTO{
    String name;
    String description;

    @Override
    public BaseEntity toEntity() {
        SubredditEntity subredditEntity = new SubredditEntity();
        subredditEntity.setName(name);
        subredditEntity.setDescription(description);
        return subredditEntity;
    }
}