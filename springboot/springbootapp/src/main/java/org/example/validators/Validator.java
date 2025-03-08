package org.example.validators;

import org.example.controller.model.ValidationData;
import org.example.persistence.repository.RedditRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Validator<T extends RedditRepository> {
    @Autowired
    protected T repository;

    public void validate(ValidationData validationData) throws Exception {
        System.out.println("Validated!");
    }
}
