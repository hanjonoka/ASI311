package com.ensta.myfilmlist.handler;

import com.ensta.myfilmlist.exception.ControllerException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ControllerException.class)
    ResponseEntity<String> controllerExceptionHandler(ControllerException e) {
        return ResponseEntity.status(400).body("Erreur de la couche service");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> argumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.status(400).body("Paramètre non valide");
    }
}
