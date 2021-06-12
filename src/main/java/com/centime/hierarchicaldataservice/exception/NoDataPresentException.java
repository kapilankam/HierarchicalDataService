package com.centime.hierarchicaldataservice.exception;

public class NoDataPresentException extends Exception {
    private String message;

    public NoDataPresentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
