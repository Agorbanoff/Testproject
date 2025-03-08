package org.example.validations;

import org.example.controller.model.ValidationData;
import org.example.validators.Validators;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SignUpValidation extends Validation{
    @Override
    public List<String> getValidatorNames() {
        List<String> validatorNames = new ArrayList<>();
        validatorNames.add(Validators.UNIQUE_USERNAME_VALIDATOR.getValue());
        return validatorNames;
    }
}
