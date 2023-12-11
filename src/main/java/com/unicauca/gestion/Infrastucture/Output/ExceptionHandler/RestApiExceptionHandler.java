package com.unicauca.gestion.Infrastucture.Output.ExceptionHandler;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.ExceptionStructure.ErrorCode;
import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.ExceptionStructure.ErrorUtils;
import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.OwnException.BussinesRulException;
import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.OwnException.EntityExistsException;
import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.OwnException.EntityNotFound;
import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.ExceptionStructure.Error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestApiExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                    final Exception ex, final Locale locale) {
        final Error error = ErrorUtils
                        .createError(ErrorCode.GENERIC_ERROR.getCode(),
                                        ErrorCode.GENERIC_ERROR.getMessageKey(),
                                        HttpStatus.INTERNAL_SERVER_ERROR.value())
                                        .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                    final EntityExistsException ex) {
        final Error error = ErrorUtils
                        .createError(ErrorCode.ENTITY_EXISTS.getCode(),
                                        String.format("%s, %s", ErrorCode.ENTITY_EXISTS.getMessageKey(),
                                        ex.getMessage()),
                                        HttpStatus.NOT_ACCEPTABLE.value())
                                        .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                    final EntityNotFound ex, final Locale locale) {
        final Error error = ErrorUtils
                        .createError(ErrorCode.ENTITY_NOT_FOUND.getCode(),
                                        String.format("%s, %s",
                                        ErrorCode.ENTITY_NOT_FOUND.getMessageKey(),
                                        ex.getMessage()),
                                        HttpStatus.NOT_FOUND.value())
                                        .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BussinesRulException.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                    final BussinesRulException ex, final Locale locale) {
        final Error error = ErrorUtils
                        .createError(ErrorCode.BUSINESS_RULE_VIOLATION.getCode(), ex.formatException(),
                                        HttpStatus.BAD_REQUEST.value())
                                        .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
                System.out.println("Returning response with identified errors");
                Map<String, String> errores = new HashMap<>();
                ex.getBindingResult().getAllErrors().forEach((error) -> {
                        String campo = ((FieldError) error).getField();
                        String mensajeDeError = error.getDefaultMessage();
                        errores.put(campo, mensajeDeError);
                });

                return new ResponseEntity<Map<String, String>>(errores, HttpStatus.BAD_REQUEST);
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(ConstraintViolationException.class)
        ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
                return new ResponseEntity<>(e.getMessage(),
                                HttpStatus.BAD_REQUEST);
        }
    
}
