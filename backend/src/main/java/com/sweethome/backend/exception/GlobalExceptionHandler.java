package com.sweethome.backend.exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorObject> handleBadRequest(RuntimeException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject
                .setStatusCode(HttpStatus.BAD_REQUEST.value())
                .setMessage(ex.getMessage())
                .setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InternalServerErrorException.class})
    public ResponseEntity<ErrorObject> handleInternalServerError(RuntimeException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject
                .setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessage(ex.getMessage())
                .setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorObject> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
        String errorMessage = fieldError.getField() + ": " + fieldError.getDefaultMessage();

        ErrorObject errorObject = new ErrorObject()
                .setStatusCode(HttpStatus.BAD_REQUEST.value())
                .setMessage(errorMessage)
                .setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleNotFound(ResourceNotFoundException ex) {
        ErrorObject errorObject = new ErrorObject()
                .setStatusCode(HttpStatus.NOT_FOUND.value())
                .setMessage(ex.getMessage())
                .setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorObject> handleJsonParseError(HttpMessageNotReadableException ex) {
        ErrorObject errorObject = new ErrorObject()
                .setStatusCode(HttpStatus.BAD_REQUEST.value())
                .setTimestamp(LocalDateTime.now());

        if (ex.getCause() instanceof InvalidFormatException invalidFormatException && invalidFormatException.getTargetType().isEnum()) {
            final String fieldName = invalidFormatException.getPath().get(0).getFieldName();
            final Object[] enumValues = invalidFormatException.getTargetType().getEnumConstants();
            final String errorMessage = "JSON parse error: Field '" + fieldName + "' accepts one of these values: " + Arrays.toString(enumValues);
            errorObject.setMessage(errorMessage);
        } else {
            errorObject.setMessage(ex.getMessage());
        }

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }
}
