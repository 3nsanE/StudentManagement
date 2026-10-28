package com.newlearn.stud_man_sys;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import javax.management.ConstructorParameters;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //404 Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException ex){
        return buildError(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    //400 Validation Error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> fieldErrors.putIfAbsent(
                        error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> body=new HashMap<>();

        body.put("status", 400);
        body.put("error", "Validation Failed");
        body.put("details", fieldErrors);

        return ResponseEntity.badRequest().body(body);
    }

    //400 Method Validation Error
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, Object>> handleMethodValidation(HandlerMethodValidationException ex){
        return  buildError(
                HttpStatus.BAD_REQUEST,"Invalid Request");
    }

    //400 Invalid Request
    @ExceptionHandler(
            org.springframework.http.converter.HttpMessageNotReadableException.class
    )
    public ResponseEntity<Map<String, Object>> handleBadRequest(
            org.springframework.http.converter.HttpMessageNotReadableException ex) {

        return buildError(
                HttpStatus.BAD_REQUEST,
                "Invalid request body: " + ex.getMostSpecificCause().getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex){
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
    }
    private ResponseEntity<Map<String, Object>> buildError(
            HttpStatus status,
            String message) {
        Map<String, Object> body = new HashMap<>();

        body.put("status",status.value());
        body.put("error",message);

        return ResponseEntity.status(status).body(body);
    }
}
