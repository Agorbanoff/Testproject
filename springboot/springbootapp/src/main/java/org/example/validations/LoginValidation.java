package org.example.validations;

import org.example.controller.model.ValidationData;
import org.example.validators.Validator;
import org.example.validators.Validators;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginValidation extends Validation{

    @Override
    public List<String> getValidatorNames() {
        List<String> validatorNames = new ArrayList<>();
        validatorNames.add(Validators.EXISTING_USER_CREDENTIALS_VALIDATOR.getValue());
        return validatorNames;
    }
}
