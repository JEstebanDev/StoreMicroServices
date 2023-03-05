package com.jestebandev.OrderService.error;

public class CustomErrorException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    public CustomErrorException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
