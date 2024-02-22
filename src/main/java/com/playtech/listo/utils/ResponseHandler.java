package com.playtech.listo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public ResponseEntity<Object> generateResponse(final HttpStatus httpStatus,
                                                   final Object response) {
        final Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK.value());
        map.put("payload", response);
        return new ResponseEntity<>(map, httpStatus);
    }

    public ResponseEntity<Object> generateResponseError(final HttpStatus httpStatus,
                                                        final MensajesErrorEnum mensajesErrorEnum) {
        final Map<String, Object> mapRespuesta = new HashMap<>();

        Map<String, Object> mapError = new HashMap<>();
        mapError.put("codigo", mensajesErrorEnum.getCodigo());
        mapError.put("mensajeError", mensajesErrorEnum.getMensajeError());
        mapError.put("recomendacion", mensajesErrorEnum.getRecomendacion());

        mapRespuesta.put("status", httpStatus.value());
        mapRespuesta.put("payload", mapError);
        return new ResponseEntity<>(mapRespuesta, httpStatus);
    }

    public ResponseEntity<Object> generateResponseErrorFormulario(final HttpStatus httpStatus,
                                                                  final String mensajeError) {

        Map<String, Object> mapError = new HashMap<>();
        mapError.put("codigo", "20");
        mapError.put("mensajeError", mensajeError);
        mapError.put("recomendacion", "Verifique la informacion ingresada");

        final Map<String, Object> mapRespuesta = new HashMap<>();
        mapRespuesta.put("status", httpStatus.value());
        mapRespuesta.put("payload", mapError);
        return new ResponseEntity<>(mapRespuesta, httpStatus);
    }


    public Map<String, Object> respuestaLogin(final Map<String, Object> datos, final HttpStatus httpStatus) {

        final Map<String, Object> map = new HashMap<>();
        map.put("payload", datos);
        map.put("status", httpStatus.value());
        return map;
    }
}