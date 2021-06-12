package com.centime.hierarchicaldataservice.aop;

import com.centime.hierarchicaldataservice.exception.NoDataPresentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class EndpointExceptionHandler {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({NoDataPresentException.class})
    public ResponseEntity<String> exception(Exception exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<String> handleConstraintViolation(
            ConstraintViolationException ex) {
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity handleDataIntegrityException(Exception ex) {
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getCause().getCause().getMessage(), HttpStatus.CONFLICT);
    }
}
