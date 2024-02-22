package com.playtech.listo.controlador.usuario;


import com.playtech.listo.controlador.usuario.dto.RegistrarUsuarioPeticionDTO;

import com.playtech.listo.servicio.usuario.UsuarioServicioImpl;
import com.playtech.listo.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.playtech.listo.utils.dto.GenericDTO;


@AllArgsConstructor
@RequestMapping("/usuario")
@RestController
public class UsuarioControlador {

    private final UsuarioServicioImpl usuarioServicio;


    @PostMapping(value = "/registrar")
    public ResponseEntity<GenericDTO> registrar(
            @RequestBody @Valid RegistrarUsuarioPeticionDTO registrarUsuarioPeticionDTO) {
        return ResponseEntity.ok().body(
                GenericDTO.correcto(usuarioServicio.registrarUsuario(registrarUsuarioPeticionDTO)));
    }

    @PostMapping("/pruebaConexion")
    public ResponseEntity<String> pruebaConexion() {

        try {
            return ResponseEntity.ok("conexion establecida");
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        }
    }


    @GetMapping("/validarPermiso")
    @PreAuthorize("hasAnyRole('crearUsuario')")
    public ResponseEntity<String> validarPermiso() {
        return ResponseEntity.ok("sisas");
    }

    @PutMapping("/asignar-modulos-usuario/{codigoUsuario}")
    public ResponseEntity<Object> asignarUsuarioModulo(
            @PathVariable Integer codigoUsuario,
            @RequestBody RegistrarUsuarioPeticionDTO registrarUsuarioPeticionDTO) {

        return new ResponseHandler().generateResponse(
                HttpStatus.OK, usuarioServicio.asignarModulosUsuario(codigoUsuario, registrarUsuarioPeticionDTO));
    }

    @GetMapping("/consultar-usuario-modulos/{codigoUsuario}")
    public ResponseEntity<Object> consultarUsuarioModulos(@PathVariable Integer codigoUsuario) {

        return new ResponseHandler().generateResponse(
                HttpStatus.OK, usuarioServicio.consultarUsuarioModulos(codigoUsuario));
    }


    @GetMapping("/consultarUsuariosRutas/{codigoUsuario}")
    public ResponseEntity<Object> consultarUsuariosRutas(@PathVariable Integer codigoUsuario) {
        return new ResponseHandler().generateResponse(
                HttpStatus.OK, usuarioServicio.traerUsuarioRuta(codigoUsuario));

    }

    @PutMapping("/asignar-rutas-usuario/{codigoUsuario}")
    public ResponseEntity<Object> asignarRutasUsuario(
            @PathVariable Integer codigoUsuario,
            @RequestBody RegistrarUsuarioPeticionDTO registrarUsuarioPeticionDTO) {

        return new ResponseHandler().generateResponse(
                HttpStatus.OK, usuarioServicio.asignarRutasUsuario(codigoUsuario, registrarUsuarioPeticionDTO));
    }

    @PostMapping("/{codigoUsuario}/rutas/{codigoRuta}")
    public ResponseEntity<Object> quitarRutaUsuario(@PathVariable Integer codigoUsuario, @PathVariable Integer codigoRuta) {
        return new ResponseHandler().generateResponse(
                HttpStatus.OK, usuarioServicio.quitarRutaUsuario(codigoUsuario, codigoRuta));
    }


}
