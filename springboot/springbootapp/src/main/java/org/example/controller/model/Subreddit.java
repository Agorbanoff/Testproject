package org.example.controller.model;

import lombok.Data;
import org.example.persistence.model.SubredditEntity;

@Data
public class Subreddit {

    String name;
    String description;

    public SubredditEntity toSubredditEntity(){
        return new SubredditEntity(null, name, description, null, null);
    }
}