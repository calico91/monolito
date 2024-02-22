package com.playtech.listo.servicio.ruta;

import com.playtech.listo.controlador.ruta.dto.RegistrarRutaPeticionDTO;
import com.playtech.listo.controlador.ruta.dto.RegistrarRutaRespuestaDTO;
import com.playtech.listo.modelo.Ruta;

import java.util.List;
import java.util.Optional;

public interface RutaService {

     RegistrarRutaRespuestaDTO registrarRutaDto(RegistrarRutaPeticionDTO registrarRutaPeticionDTO);

     List<Ruta> traerTodasRutas();

     Ruta traerRutasId(Integer id);

     RegistrarRutaRespuestaDTO actualizarRutasId(Integer id, RegistrarRutaPeticionDTO registrarRutaPeticionDTO) throws  Exception;

     Ruta inactivarRuta(Integer codigoRuta, String estado);


}
