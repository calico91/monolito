package com.playtech.listo.servicio.usuario;

import com.playtech.listo.controlador.usuario.dto.RegistrarUsuarioPeticionDTO;
import com.playtech.listo.controlador.usuario.dto.RegistrarUsuarioRespuestaDTO;
import com.playtech.listo.modelo.SubModulo;
import com.playtech.listo.modelo.Ruta;
import com.playtech.listo.modelo.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UsuarioServicio {

    RegistrarUsuarioRespuestaDTO registrarUsuario(RegistrarUsuarioPeticionDTO registrarUsuarioPeticionDTO);

    Usuario asignarModulosUsuario(Integer codigoUsuario, RegistrarUsuarioPeticionDTO registrarUsuarioPeticionDTO);

    Set<SubModulo> consultarUsuarioModulos(Integer codigoUsuario);


    Optional<Usuario> consultarUsuariosModulos(Integer codigoUsuario);


    List<Ruta> traerUsuarioRuta(Integer codigoUsuario);

    Set<Ruta> asignarRutasUsuario(Integer codigoUsuario, RegistrarUsuarioPeticionDTO registrarUsuarioPeticionDTO);

    Usuario quitarRutaUsuario(Integer codigoUsuario, Integer codigoRuta);
}
