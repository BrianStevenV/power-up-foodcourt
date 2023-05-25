package com.example.foodcourtmicroservice.configuration;

import com.example.foodcourtmicroservice.domain.exceptions.IdPlateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(IdPlateNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleIdPlateNotFouncException(IdPlateNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(Constants.RESPONSE_ERROR_MESSAGE_KEY, Constants.ID_UPDATE_NOT_FOUND));
    }
}
