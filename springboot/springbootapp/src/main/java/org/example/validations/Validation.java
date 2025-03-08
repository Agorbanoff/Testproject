package org.example.validations;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.controller.model.ValidationData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public abstract class Validation {
    public List<String> getValidatorNames() {
        return new ArrayList<>();
    }


}
