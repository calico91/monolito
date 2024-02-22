package com.playtech.listo.mapper;

import com.playtech.listo.controlador.login.dto.LoginRespuestaDTO;
import com.playtech.listo.controlador.usuario.dto.RegistrarUsuarioPeticionDTO;
import com.playtech.listo.controlador.usuario.dto.RegistrarUsuarioRespuestaDTO;
import com.playtech.listo.modelo.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {RutaMapper.class, SubModuloMapper.class})
public interface UsuarioMapper {

    UsuarioMapper MAPPER = Mappers.getMapper(UsuarioMapper.class);


    Usuario toUsuario(RegistrarUsuarioPeticionDTO registrarUsuarioPeticionDTO);

    RegistrarUsuarioRespuestaDTO toRegistrarUsuarioRespuestaDTO(Usuario usuario);
    LoginRespuestaDTO toLoginRespuestaDTO(Usuario usuario);
}
