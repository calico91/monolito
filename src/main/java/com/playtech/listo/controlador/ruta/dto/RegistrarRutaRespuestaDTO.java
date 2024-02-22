package com.playtech.listo.controlador.ruta.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarRutaRespuestaDTO {

    Integer codigoRuta;
    String descripcion;
    String celular;
    String estado;
}

