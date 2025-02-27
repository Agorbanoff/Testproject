package org.example.validators;

import org.example.controller.model.UserCredentials;
import org.example.exception.exceptions.UsernameAlreadyExistsException;
import org.example.persistence.repository.UserProfileRepository;

public class UniqueUsernameValidator extends Validator<UserProfileRepository, UserCredentials>{
    @Override
    public void validate(UserCredentials userCredentials) throws UsernameAlreadyExistsException {
        if (repository.existsByUsername(userCredentials.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }
    }
}
