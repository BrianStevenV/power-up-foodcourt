package com.example.foodcourtmicroservice.configuration;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.exceptions.DataDuplicateViolationException;
import com.example.foodcourtmicroservice.domain.exceptions.IdPlateNotFoundException;
import com.example.foodcourtmicroservice.domain.exceptions.NoProviderException;
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

    @ExceptionHandler(NoProviderException.class)
    public ResponseEntity<Map<String, String>> handleNoProviderException(NoProviderException noProviderException){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(Constants.RESPONSE_ERROR_MESSAGE_KEY, Constants.NO_PROVIDER_PERMISSION));
    }

    @ExceptionHandler(DataDuplicateViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataDuplicateViolationException(DataDuplicateViolationException dataDuplicateViolationException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(Constants.RESPONSE_ERROR_MESSAGE_KEY, Constants.DATA_DUPLICATE_RESTAURANT_DTO));
    }
}
