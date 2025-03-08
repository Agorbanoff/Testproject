package org.example.validators;

import org.example.controller.model.UserCredentials;
import org.example.controller.model.ValidationData;
import org.example.exception.exceptions.UsernameAlreadyExistsException;
import org.example.persistence.repository.UserProfileRepository;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator extends Validator<UserProfileRepository>{
    @Override
    public void validate(ValidationData validationData) throws UsernameAlreadyExistsException {
        String username = validationData.getUserCredentials().getUsername();
        if (repository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }
    }
}
