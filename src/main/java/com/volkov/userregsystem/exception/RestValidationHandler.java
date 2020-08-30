package com.volkov.userregsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<FieldValidationErrorDetails> handleValidationError(
            MethodArgumentNotValidException mNotValidException,
            HttpServletRequest request) {

        FieldValidationErrorDetails fErrorsDetails = new FieldValidationErrorDetails();

        fErrorsDetails.setError_timeStamp(new Date().getTime());
        fErrorsDetails.setError_status(HttpStatus.BAD_REQUEST.value());
        fErrorsDetails.setError_title("Field validation error");
        fErrorsDetails.setError_detail("Init field validation failed");
        fErrorsDetails.setError_developer_Message(mNotValidException.getClass().getName());
        fErrorsDetails.setError_path(request.getRequestURI());

        BindingResult result= mNotValidException.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        for (FieldError eachError : fieldErrors) {
            FieldValidationError fError = processFieldError(eachError);
            List<FieldValidationError> fValidationErrorList = fErrorsDetails.getErrors().get(eachError.getField());
            if (fValidationErrorList == null) {
                fValidationErrorList = new ArrayList<>();
            }
            fValidationErrorList.add(fError);
            fErrorsDetails.getErrors().put(eachError.getField(), fValidationErrorList);
        }

        return new ResponseEntity<FieldValidationErrorDetails>(fErrorsDetails, HttpStatus.BAD_REQUEST);
    }

    private FieldValidationError processFieldError (final FieldError error) {
        FieldValidationError fieldValidationError = new FieldValidationError();
        if (error != null) {
            fieldValidationError.setField(error.getField());
            fieldValidationError.setType(FieldValidationError.MessageType.ERROR);
            fieldValidationError.setMessage(error.getDefaultMessage());
        }
        return fieldValidationError;
    }
}
