package com.playtech.listo.mapper;

import com.playtech.listo.controlador.ruta.dto.RegistrarRutaPeticionDTO;
import com.playtech.listo.controlador.ruta.dto.RegistrarRutaRespuestaDTO;
import com.playtech.listo.modelo.Ruta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RutaMapper {

    RegistrarRutaRespuestaDTO rutaToRegistrarRutaRespuestaDTO(Ruta ruta);

    Ruta registrarRutaRespuestaDTOToRuta(RegistrarRutaPeticionDTO registrarRutaPeticionDTO);
}
