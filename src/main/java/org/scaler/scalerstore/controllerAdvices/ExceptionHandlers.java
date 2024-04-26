package org.scaler.scalerstore.controllerAdvices;

import org.scaler.scalerstore.dtos.ExceptionDTO;
import org.scaler.scalerstore.dtos.ResStatus;
import org.scaler.scalerstore.exceptions.ProductNotFoundException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler
    public void runTimeException(Exception e){
        System.out.println(e.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> productNotFoundException(ProductNotFoundException productNotFoundException){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(productNotFoundException.getMessage());
        exceptionDTO.setStatus(ResStatus.FAILURE);
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
