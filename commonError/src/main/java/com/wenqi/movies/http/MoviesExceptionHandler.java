package com.wenqi.movies.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenqi.movies.http.entity.HttpError;
import com.wenqi.movies.service.exception.MovieException;

@ControllerAdvice
public class MoviesExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({MovieException.class})
    @ResponseBody
    public ResponseEntity<HttpError> handleIllegalArgumentException(MovieException e) {
        logger.info("Movies Exception handler ", e);
        return new ResponseEntity<>(new HttpError(e), HttpStatus.CONFLICT);
    }
}
