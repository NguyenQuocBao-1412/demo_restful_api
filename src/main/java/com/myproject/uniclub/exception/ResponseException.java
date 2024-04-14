package com.myproject.uniclub.exception;

import com.google.gson.Gson;
import com.myproject.uniclub.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ResponseException {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleException(RuntimeException e) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setMessage(e.getMessage());
        response.setData("");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidException(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(item -> {
            String field = ((FieldError) item).getField();
            String message = item.getDefaultMessage();

            errors.put(field, message);
        });

        Gson gson = new Gson();

        BaseResponse response = new BaseResponse();
        response.setStatusCode(400);
        response.setMessage("");
        response.setData(errors);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(InsertException.class)
    ResponseEntity<?> handleInsertException(InsertException exception) {
        ErrorStatusCode errorStatusCode = exception.getErrorStatusCode();

        BaseResponse response = new BaseResponse();
        response.setStatusCode(errorStatusCode.getStatusCode());
        response.setMessage(errorStatusCode.getMessage());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
