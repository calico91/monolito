package com.playtech.listo.utils.dto;

import com.playtech.listo.utils.MensajesErrorEnum;
import lombok.Data;


@Data
public class ErrorDTO {

    String recomendacion;
    String codigo;
    String error;

    public static ErrorDTO construirError(MensajesErrorEnum mensajeError) {


        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCodigo(mensajeError.getCodigo());
        errorDTO.setError(mensajeError.getMensajeError());
        errorDTO.setRecomendacion(mensajeError.getRecomendacion());

        return errorDTO;
    }

}
