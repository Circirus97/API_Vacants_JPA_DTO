package com.riwi.vacants.controllers.errors;


import com.riwi.vacants.utils.dto.errors.BaseErrorResponse;
import com.riwi.vacants.utils.dto.errors.ErrorResponse;
import com.riwi.vacants.utils.dto.errors.ErrorsResponse;
import com.riwi.vacants.utils.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Error creado por nosotros///////////
 */
/**
 * RestControllerAdvice = Controlador de errores
 */
@RestControllerAdvice

/**
 * Status de error
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestcontroller {

    /**
     *
      * Para especificar cuando se va a disparar este metodo, utilizamos la anotacion ExceptionsHandler
     */
    @ExceptionHandler(IdNotFoundException.class)
    public BaseErrorResponse handleIdNotFound(IdNotFoundException exception){

        return ErrorResponse.builder().
                message(exception.getMessage()).
                status(HttpStatus.BAD_REQUEST.name()).
                code(HttpStatus.BAD_REQUEST.value()).
                build();

        /**
         * Esto (Arriba) es lo mismo que esto(Abajo)
         */

        /*ErrorResponse error = new ErrorResponse();

                error.setMessage(exception.getMessage());
                error.setStatus(HttpStatus.BAD_REQUEST.name());
                error.setCode(HttpStatus.BAD_REQUEST.value());

        */
    }

/**
 * Error creado con la libreria
 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleErrors(MethodArgumentNotValidException exception){

        List<String> errors = new ArrayList<>();

        exception.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

        return ErrorsResponse.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }


}
