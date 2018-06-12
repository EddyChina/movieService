package com.wenqi.movies.http.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wenqi.movies.service.exception.MovieException;

@JsonInclude(value = Include.NON_NULL)
public class HttpError {
    public int status;
    public String code;
    public String message;
    public String debug;

    protected HttpError() {

    }

    public HttpError(MovieException ex) {
        status = 409;
        code = ex.getErrorCode() == null ? "" : ex.getErrorCode().name();
        message = ex.getMessage();
        debug = ex.getCause() == null ? "" : "Caused by " + ex.getCause().getMessage();
    }
}
