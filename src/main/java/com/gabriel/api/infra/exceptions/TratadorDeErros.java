package com.gabriel.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ErrosDeValidacao::new).toList());
    }

    private record ErrosDeValidacao(
            String campo,
            String messagem
    ){
        public ErrosDeValidacao(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity tratarErroValidacao(ValidationException ex){
        DadosValidadorException dadosValidadorException = new DadosValidadorException(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(dadosValidadorException);
    }

    private record DadosValidadorException(
            LocalDateTime timeStamp,
            String error){
        public DadosValidadorException(String error, LocalDateTime timeStamp){
            this(
                    timeStamp,
                    error
            );
        }
    }

}
