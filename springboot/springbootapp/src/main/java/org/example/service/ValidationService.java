package org.example.service;

import org.example.controller.model.ValidationData;
import org.example.validations.Validation;
import org.example.validators.Validator;

import java.util.Collection;

public interface ValidationService {
    void validate(Class<? extends Validation> validationClass, ValidationData validationData) throws Exception;
}
