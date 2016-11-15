
package com.teamexcalibur;

/**
 * 
 * @author 
 */
public class ValidationError {
public String fieldName;
public String message;

    public ValidationError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public ValidationError() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }


}
