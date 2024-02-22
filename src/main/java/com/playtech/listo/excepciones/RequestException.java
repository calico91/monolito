package com.playtech.listo.excepciones;

import com.playtech.listo.utils.MensajesErrorEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestException extends RuntimeException {
    private final Integer status;
    private final MensajesErrorEnum mensajesErrorEnum;

    public RequestException(MensajesErrorEnum mensajesErrorEnum, Integer status) {
        this.status = status;
        this.mensajesErrorEnum = mensajesErrorEnum;
    }
}
