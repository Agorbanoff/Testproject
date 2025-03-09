package org.example.validators;

import org.example.controller.model.ValidationData;
import org.example.exception.exceptions.UserNotInSubredditException;
import org.example.persistence.repository.SubredditRepository;
import org.springframework.stereotype.Component;

@Component
public class UserAlreadyJoinedSubredditValidator extends Validator<SubredditRepository>{
    @Override
    public void validate(ValidationData validationData) throws Exception {
        if (!repository.existsByUsers_Session_SessionStringAndId(
                validationData.getSessionString(),
                validationData.getJoinSubredditRequestDTO().getId())) {
            throw new UserNotInSubredditException("User not in subreddit!");
        }
    }
}
