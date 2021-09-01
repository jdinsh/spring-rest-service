package com.djlearning.springrestservice.exception;

import com.djlearning.springrestservice.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice //specilization to declare methods on multicontroller classes
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public  ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new  ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
        ResponseEntity responseEntity = new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        return  responseEntity;
    }

    @ExceptionHandler({UserNotFoundException.class})
    public  ResponseEntity<Object> handleUserNotFoundlException(UserNotFoundException userNotFoundException, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new  ExceptionResponse(new Date(),userNotFoundException.getMessage(), request.getDescription(false));
        ResponseEntity responseEntity = new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
        return  responseEntity;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new  ExceptionResponse(new Date(),"VALIDATION FAILED",  ex.getBindingResult().toString());
        ResponseEntity responseEntity = new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
        return  responseEntity;

    }

}
