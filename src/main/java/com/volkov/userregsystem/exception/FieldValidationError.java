package com.volkov.userregsystem.exception;

import lombok.Data;

@Data
public class FieldValidationError {

    private String field;
    private String message;
    private MessageType type;

    public enum MessageType {
        SUCCESS,
        INFO,
        WARNING,
        ERROR
    }
}
