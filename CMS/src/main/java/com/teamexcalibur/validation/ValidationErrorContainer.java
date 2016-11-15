package com.teamexcalibur.validation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class ValidationErrorContainer {

    private List<ValidationError> validationErrors = new ArrayList<>();

    public void addValidationError(String field, String message) {
        ValidationError error = new ValidationError(field, message);
        validationErrors.add(error);

    }

    public List<ValidationError> getFieldErrors() {
        return validationErrors;
    }

}
