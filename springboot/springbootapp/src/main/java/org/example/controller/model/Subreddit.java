package org.example.controller.model;

import lombok.Data;
import org.example.persistence.model.SubredditEntity;

@Data
public class Subreddit {
    Long id;
    String name;
    String description;

    public SubredditEntity toSubredditEntity(){
        SubredditEntity subredditEntity = new SubredditEntity();
        subredditEntity.setId(id);
        subredditEntity.setName(name);
        subredditEntity.setDesctription(description);
        return subredditEntity;
    }
}