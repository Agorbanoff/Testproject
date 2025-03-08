package org.example.validators;

import org.example.controller.model.ValidationData;
import org.example.exception.exceptions.NullIdInCreateRequestException;
import org.example.persistence.repository.RedditRepository;

public class UniquePostValidator extends Validator<RedditRepository>{
    @Override
    public void validate(ValidationData validationData) throws NullIdInCreateRequestException {
        if (validationData.getPost().getId() != null) {
            throw new NullIdInCreateRequestException("Id must be null for create requests!");
        }
    }
}
