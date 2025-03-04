package org.example.validators;

import org.example.controller.model.UserCredentials;
import org.example.exception.exceptions.UsernameAlreadyExistsException;
import org.example.persistence.repository.UserProfileRepository;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator extends Validator<UserProfileRepository, String>{
    @Override
    public void validate(String username) throws UsernameAlreadyExistsException {
        if (repository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }
    }
}
