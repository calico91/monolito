package com.playtech.listo.controlador.usuario.dto;

import com.playtech.listo.modelo.SubModulo;
import com.playtech.listo.modelo.Ruta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrarUsuarioRespuestaDTO {

    Integer codigoUsuario;
    String nombre;
    String apellido;
    String login;

    Set<SubModulo> subModulos;
    Set<Ruta> rutas;
}
