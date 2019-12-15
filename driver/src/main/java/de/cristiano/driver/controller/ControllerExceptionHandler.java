package de.cristiano.driver.controller;

import de.cristiano.driver.exception.CarAlreadyInUseException;
import de.cristiano.driver.exception.ConstraintsViolationException;
import de.cristiano.driver.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.lang.String.format;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler
{
    private final static HttpHeaders JSON_RESPONSE;


    static
    {
        JSON_RESPONSE = new HttpHeaders();
        JSON_RESPONSE.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    }


    @ExceptionHandler(value = ConstraintsViolationException.class)
    public ResponseEntity<Object> handleConstraintsViolationException(Exception ex, WebRequest request)
    {
        return handleExceptionInternal(ex, format("{\"message\": \"%s\"}", ex.getMessage()), JSON_RESPONSE, BAD_REQUEST, request);
    }


    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request)
    {
        return handleExceptionInternal(ex, format("{\"message\": \"%s\"}", ex.getMessage()), JSON_RESPONSE, NOT_FOUND, request);
    }

    @ExceptionHandler(value = CarAlreadyInUseException.class)
    public ResponseEntity<Object> handleCarAlreadyInUseException(Exception ex, WebRequest request)
    {
        return handleExceptionInternal(ex, format("{\"message\": \"%s\"}", ex.getMessage()), JSON_RESPONSE, CONFLICT, request);
    }
}

