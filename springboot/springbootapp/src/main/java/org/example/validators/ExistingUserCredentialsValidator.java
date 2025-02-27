package org.example.validators;

import org.example.controller.model.UserCredentials;
import org.example.exception.exceptions.UserNotFoundException;
import org.example.persistence.repository.UserProfileRepository;

public class ExistingUserCredentialsValidator extends Validator<UserProfileRepository, UserCredentials>{

    @Override
    public void validate(UserCredentials userCredentials) throws UserNotFoundException {
        if (!repository.existsByUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword())) {
            throw new UserNotFoundException("User not found!");
        }
    }
}
