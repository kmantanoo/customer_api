package com.example.customer_api.controllers.advices;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class RegisterControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put("errorMsg", String.join(" ", fieldName, errorMessage));
        });
        return errors;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Map<String, String> handleConstraintException(SQLIntegrityConstraintViolationException ex) {
        Map<String, String> error = new HashMap<>();
        if (ex.getMessage().startsWith("Duplicate entry ")) {
            error.put("errorMsg", "Duplicate username '" +
                  ex.getMessage().substring(17, ex.getMessage().indexOf('\'',18)) + "'");
        } else {
            error.put("errorMsg", ex.getMessage());
        }
        return error;
    }
}
