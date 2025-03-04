package org.example.validators;

import org.example.controller.model.Subreddit;
import org.example.exception.exceptions.SubredditNotFoundException;
import org.example.persistence.repository.SubredditRepository;
import org.springframework.stereotype.Component;

@Component
public class ExistingSubredditValidator extends Validator<SubredditRepository, Long>{
    @Override
    public void validate(Long subredditId) throws SubredditNotFoundException {
        if (!repository.existsById(subredditId)) {
            throw new SubredditNotFoundException("Subreddit not found!");
        }
    }
}
