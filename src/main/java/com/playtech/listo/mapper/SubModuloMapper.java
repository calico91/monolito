package com.playtech.listo.mapper;

import com.playtech.listo.controlador.modulo.dto.SubModuloDTO;
import com.playtech.listo.modelo.SubModulo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubModuloMapper {

    SubModuloDTO subModuloToSubModuloDTO(SubModulo subModulo);

    SubModulo subModuloDTOToSubModulo(SubModuloDTO subModuloDTO);

    List<SubModuloDTO> subModuloListToSubModuloDTOList(List<SubModulo> subModuloList);

    List<SubModulo> subModuloDTOListToSubModuloList(List<SubModuloDTO> subModuloDTOList);

}
