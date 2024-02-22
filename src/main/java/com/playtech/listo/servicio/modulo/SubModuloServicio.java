package com.playtech.listo.servicio.modulo;

import com.playtech.listo.controlador.modulo.dto.SubModuloDTO;
import com.playtech.listo.modelo.SubModulo;

import java.util.List;
public interface SubModuloServicio {

    SubModuloDTO consultarSubModulo(Integer codigoSubModulo);
    List<SubModuloDTO> consultarSubModulos();

}
