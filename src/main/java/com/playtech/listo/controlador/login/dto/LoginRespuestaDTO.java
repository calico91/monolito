package com.playtech.listo.controlador.login.dto;

import com.playtech.listo.modelo.SubModulo;
import com.playtech.listo.modelo.Ruta;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRespuestaDTO {

    Integer codigoUsuario;
    String nombre;
    String apellido;
    String login;

    Set<SubModulo> subModulos;
    Set<Ruta> rutas;
    String token;
}
