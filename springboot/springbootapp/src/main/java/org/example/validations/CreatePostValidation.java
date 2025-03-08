package org.example.validations;

import org.example.validators.Validators;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreatePostValidation extends Validation{

    @Override
    public List<String> getValidatorNames() {
        List<String> validatorNames = new ArrayList<>();
        validatorNames.add(Validators.EXISTING_SUBREDDIT_VALIDATOR.getValue());
        validatorNames.add(Validators.USER_ALREADY_JOINED_SUBREDDIT_VALIDATOR.getValue());
        validatorNames.add(Validators.NULL_ID_IN_CREATE_REQUEST_VALIDATOR.getValue());
        return validatorNames;
    }
}
