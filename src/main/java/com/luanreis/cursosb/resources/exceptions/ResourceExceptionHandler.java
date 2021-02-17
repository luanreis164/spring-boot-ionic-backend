package com.luanreis.cursosb.resources.exceptions;

import com.luanreis.cursosb.domain.Categoria;
import com.luanreis.cursosb.services.exceptions.DataIntegrityException;
import com.luanreis.cursosb.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> idNotFound(ObjectNotFoundException exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Categoria não encontrada.");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<Object> dataIntegrity(DataIntegrityException dataexception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Não é possivel excluir uma categoria que possui produtos.");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }






}