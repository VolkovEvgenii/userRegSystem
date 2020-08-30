package com.volkov.userregsystem.exception;

import com.volkov.userregsystem.dto.UserDTO;

public class CustomErrorType extends UserDTO {

    private String errorMessage;

    public CustomErrorType (final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
