package org.example.controller.model;

import lombok.Data;
import org.example.persistence.model.BaseEntity;
import org.example.persistence.model.PostEntity;

@Data
public class CreatePostRequestDTO extends DTO{
    private String title;
    private String text;

    @Override
    public BaseEntity toEntity() {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(title);
        postEntity.setText(text);
        return postEntity;
    }
}
