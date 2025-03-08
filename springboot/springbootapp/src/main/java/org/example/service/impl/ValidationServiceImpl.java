package org.example.service.impl;

import org.example.Application;
import org.example.controller.model.ValidationData;
import org.example.service.ValidationService;
import org.example.validations.Validation;
import org.example.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ValidationServiceImpl implements ValidationService {

    private Map<String, Validator> validators;

    @Autowired
    public ValidationServiceImpl(ApplicationContext applicationContext) {
        validators = applicationContext.getBeansOfType(Validator.class);
    }


    public void validate(Class<? extends Validation> validationClass, ValidationData validationData) throws Exception{
        Validation validation = validationClass.getDeclaredConstructor().newInstance();
        List<String> validatorNames = validation.getValidatorNames();
        for (String validatorName : validatorNames) {
            validators.get(validatorName).validate(validationData);
        }
    }
}
