package org.example.validators;

import org.example.exception.exceptions.SubredditAlreadyExistsException;
import org.example.persistence.repository.SubredditRepository;
import org.springframework.stereotype.Component;

@Component
public class UniqueSubredditValidator extends Validator<SubredditRepository, String> {
    @Override
    public void validate(String subredditName) throws SubredditAlreadyExistsException {
        if (repository.existsByName(subredditName)) {
            throw new SubredditAlreadyExistsException("Subreddit already exists!");
        }
    }
}
