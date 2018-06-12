package com.wenqi.movies.service.exception;

public class MovieException extends RuntimeException {
    private ErrorCode errorCode;

    public MovieException(ErrorCode code, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = code;
    }

    public MovieException(ErrorCode code, String message) {
        super(message);
        this.errorCode = code;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
