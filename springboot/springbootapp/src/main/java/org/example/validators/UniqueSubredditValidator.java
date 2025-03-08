package org.example.validators;

import org.example.controller.model.ValidationData;
import org.example.exception.exceptions.SubredditAlreadyExistsException;
import org.example.persistence.repository.SubredditRepository;
import org.springframework.stereotype.Component;

@Component
public class UniqueSubredditValidator extends Validator<SubredditRepository> {
    @Override
    public void validate(ValidationData validationData) throws SubredditAlreadyExistsException {
        String subredditName = validationData.getSubreddit().getName();
        if (repository.existsByName(subredditName)) {
            throw new SubredditAlreadyExistsException("Subreddit already exists!");
        }
    }
}
