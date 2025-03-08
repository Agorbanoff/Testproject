package org.example.validators;

import org.example.controller.model.Subreddit;
import org.example.controller.model.ValidationData;
import org.example.exception.exceptions.SubredditNotFoundException;
import org.example.persistence.repository.SubredditRepository;
import org.springframework.stereotype.Component;

@Component
public class ExistingSubredditValidator extends Validator<SubredditRepository>{
    @Override
    public void validate(ValidationData validationData) throws SubredditNotFoundException {
        Long subredditId = validationData.getSubreddit().getId();
        if (!repository.existsById(subredditId)) {
            throw new SubredditNotFoundException("Subreddit not found!");
        }
    }
}
