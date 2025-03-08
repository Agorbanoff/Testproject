package org.example.validators;

import org.example.controller.model.UserCredentials;
import org.example.controller.model.ValidationData;
import org.example.exception.exceptions.UserNotFoundException;
import org.example.persistence.repository.UserProfileRepository;
import org.springframework.stereotype.Component;

@Component
public class ExistingUserCredentialsValidator extends Validator<UserProfileRepository>{

    @Override
    public void validate(ValidationData validationData) throws UserNotFoundException {
        UserCredentials userCredentials = validationData.getUserCredentials();
        if (!repository.existsByUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword())) {
            throw new UserNotFoundException("User not found!");
        }
    }
}
