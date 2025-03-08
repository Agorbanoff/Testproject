package org.example.validations;

import org.example.controller.model.ValidationData;
import org.example.validators.Validators;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JoinSubredditValidation extends Validation{

    @Override
    public List<String> getValidatorNames() {
        List<String> validatorNames = new ArrayList<>();
        validatorNames.add(Validators.EXISTING_SUBREDDIT_VALIDATOR.getValue());
        return validatorNames;
    }
}
