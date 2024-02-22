package com.playtech.listo.excepciones;

import com.playtech.listo.utils.MensajesErrorEnum;
import com.playtech.listo.utils.ResponseHandler;
import com.playtech.listo.utils.dto.GenericDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(value = RequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> requestException(RequestException ex) {

        return new ResponseHandler().generateResponseError(
                HttpStatus.BAD_REQUEST, ex.getMensajesErrorEnum());

    }

    @ExceptionHandler(value = NoFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> noFoundException(NoFoundException ex) {

        return ResponseEntity.ok().body(GenericDTO.error(ex.getMensajesErrorEnum(), HttpStatus.UNAUTHORIZED.value()));

    }

    /**
     * excepcion que se genera cuando los formularios no cumplen con las validaciones
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {


        String error = "El campo ".concat(ex.getBindingResult().getFieldErrors().get(0).getField().concat(
                " " + Objects.requireNonNull(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage())));

        return ResponseEntity.ok().body(GenericDTO.errorFormulario(error, HttpStatus.UNAUTHORIZED.value()));

    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> accessDeniedException() {

        return ResponseEntity.ok().body(GenericDTO.error(
                MensajesErrorEnum.ACCESO_DENEGADO_POR_PERMISOS, HttpStatus.UNAUTHORIZED.value()));


    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> httpMessageNotReadableException() {

        return ResponseEntity.ok().body(GenericDTO.error(
                MensajesErrorEnum.FORMATO_PETICION_INCORRECTO, HttpStatus.UNAUTHORIZED.value()));

    }

}
