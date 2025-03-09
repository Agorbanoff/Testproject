package org.example.validators;

import org.example.controller.model.ValidationData;
import org.example.exception.exceptions.NullIdInCreateRequestException;
import org.example.persistence.repository.RedditRepository;
import org.example.persistence.repository.UserAccountRepository;
import org.springframework.stereotype.Component;

import javax.lang.model.type.NullType;

@Component
public class UniquePostValidator extends Validator<UserAccountRepository>{
    @Override
    public void validate(ValidationData validationData) throws NullIdInCreateRequestException {
        if (validationData.getJoinSubredditRequestDTO().getId() != null) {
            throw new NullIdInCreateRequestException("Id must be null for create requests!");
        }
    }
}
