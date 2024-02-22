package com.playtech.listo.excepciones;

import com.playtech.listo.utils.MensajesErrorEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoFoundException extends RuntimeException {
    private final Integer status;
    private final MensajesErrorEnum mensajesErrorEnum;

    public NoFoundException(MensajesErrorEnum mensajesErrorEnum, Integer status) {
        this.status = status;
        this.mensajesErrorEnum = mensajesErrorEnum;
    }
}