package com.playtech.listo.utils.dto;

import com.playtech.listo.utils.MensajesErrorEnum;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GenericDTO {

    private Integer status;
    private Object payload;

    public static GenericDTO correcto(Object data) {

        GenericDTO genericDto = new GenericDTO();
        genericDto.setStatus(HttpStatus.OK.value());
        genericDto.setPayload(data);

        return genericDto;
    }

    public static GenericDTO error(MensajesErrorEnum mensajeError, Integer httpStatus) {

        GenericDTO genericDto = new GenericDTO();
        genericDto.setStatus(httpStatus);
        genericDto.setPayload(ErrorDTO.construirError(mensajeError));

        return genericDto;
    }

    public static GenericDTO errorFormulario(String mensajeError, Integer httpStatus) {

        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setRecomendacion("Verifique los datos ingresados");
        errorDTO.setCodigo("20");
        errorDTO.setError(mensajeError);

        GenericDTO genericDto = new GenericDTO();
        genericDto.setStatus(httpStatus);
        genericDto.setPayload(errorDTO);

        return genericDto;
    }
}
