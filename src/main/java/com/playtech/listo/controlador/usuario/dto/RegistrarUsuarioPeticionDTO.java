package com.playtech.listo.controlador.usuario.dto;

import com.playtech.listo.modelo.SubModulo;
import com.playtech.listo.modelo.Ruta;
import jakarta.validation.constraints.NotBlank;
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
public class RegistrarUsuarioPeticionDTO {

    @NotBlank(message = "no debe ser nulo o vacio")
    String nombre;
    @NotBlank(message = "no debe ser nulo o vacio")
    String apellido;
    @NotBlank(message = "no debe ser nulo o vacio")
    String login;
    @NotBlank(message = "no debe ser nulo o vacio")
    String clave;

    Set<SubModulo> subModulos;
    Set<Ruta> rutas;
}
