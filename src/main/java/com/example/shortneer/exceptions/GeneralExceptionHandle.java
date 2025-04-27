package com.example.shortneer.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GeneralExceptionHandle {
    Map<String,Object> response = new LinkedHashMap<>();
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleUrlValidationException(MethodArgumentNotValidException ex){
        List<ObjectError> listError = ex.getAllErrors();
        String messageError = listError.get(0).getDefaultMessage();
        response.put("time-stamp", LocalDateTime.now());
        response.put("status",HttpStatus.BAD_REQUEST.value());
        response.put("error","Validation URL exception");
        response.put("message",messageError);


        return ResponseEntity.badRequest().body(response);
    }
        @ExceptionHandler(ParameterException.class)
    public ResponseEntity<Map<String,Object>> handleRequestParameterException(ParameterException ex){
        
        response.put("time-stamp", LocalDateTime.now());
        response.put("status",HttpStatus.BAD_REQUEST.value());
        response.put("error","Validation Request Parameter exception");
        response.put("message",ex.getMessage());


        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<Map<String,Object>> handleInternalErrorException(InternalErrorException ex){
        
        response.put("time-stamp", LocalDateTime.now());
        response.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error","Internal Server Error");
        response.put("message",ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> customException(Exception ex){
        
        response.put("time-stamp", LocalDateTime.now());
        response.put("status",ex.getStatus().value());
        response.put("error","Internal Server Error");
        response.put("message",ex.getMessage());
        return new ResponseEntity<>(response,ex.getStatus());
    }
}
