package com.algaworks.algaworks.osworksapi.api.exceptionhandler;

import com.algaworks.algaworks.osworksapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algaworks.osworksapi.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var campos = new ArrayList<Problema.Campo>();

        for (ObjectError error: ex.getBindingResult().getAllErrors()){
            campos.add(new Problema.Campo(((FieldError) error).getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale())));
        }

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
        problema.setDataHora(OffsetDateTime.now());
        problema.setCampos(campos);
        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
        problema.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request){
        var status = HttpStatus.NOT_FOUND;
        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
        problema.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
}
