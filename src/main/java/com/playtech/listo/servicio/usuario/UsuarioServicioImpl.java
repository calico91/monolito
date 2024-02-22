package com.playtech.listo.servicio.usuario;

import com.playtech.listo.controlador.usuario.dto.RegistrarUsuarioPeticionDTO;
import com.playtech.listo.controlador.usuario.dto.RegistrarUsuarioRespuestaDTO;
import com.playtech.listo.excepciones.NoFoundException;
import com.playtech.listo.excepciones.RequestException;
import com.playtech.listo.mapper.UsuarioMapper;
import com.playtech.listo.modelo.SubModulo;
import com.playtech.listo.modelo.Ruta;
import com.playtech.listo.modelo.Usuario;
import com.playtech.listo.repositorio.SubModuloRepositorio;
import com.playtech.listo.repositorio.RolesRepositorio;
import com.playtech.listo.repositorio.RutaRepositorio;
import com.playtech.listo.repositorio.UsuarioRepositorio;
import com.playtech.listo.utils.MensajesErrorEnum;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@Service
@Slf4j
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final SubModuloRepositorio subModuloRepositorio;
    private final RolesRepositorio rolesRepositorio;
    private final RutaRepositorio rutaRepositorio;

    @Override
    public RegistrarUsuarioRespuestaDTO registrarUsuario(
            RegistrarUsuarioPeticionDTO registrarUsuarioPeticionDTO) throws RequestException, NoFoundException {

        log.info("registrarUsuario peticion:".concat(registrarUsuarioPeticionDTO.toString()));

        if (usuarioRepositorio.findByLogin(registrarUsuarioPeticionDTO.getLogin()).isPresent())
            throw new RequestException(
                    MensajesErrorEnum.USUARIO_REGISTRADO, HttpStatus.BAD_REQUEST.value());

        log.info("codigo:" + registrarUsuarioPeticionDTO.getSubModulos());

        Set<SubModulo> authorities = registrarUsuarioPeticionDTO.getSubModulos().stream()
                .map(modulo -> subModuloRepositorio.findById(modulo.getCodigoSubModulo()).orElseThrow(
                        () -> new NoFoundException(MensajesErrorEnum.DATOS_NO_ENCONTRADOS, HttpStatus.NOT_FOUND.value())
                )).collect(Collectors.toSet());

        log.info("codigo2:" + authorities);

        try {
            Usuario usuario = UsuarioMapper.MAPPER.toUsuario(registrarUsuarioPeticionDTO);

            usuario.setClave(passwordEncoder.encode(registrarUsuarioPeticionDTO.getClave()));
            usuario.setSubModulos(authorities);
            usuario.setEstado("A");

            RegistrarUsuarioRespuestaDTO usuarioRegistrado = UsuarioMapper.
                    MAPPER.toRegistrarUsuarioRespuestaDTO(usuarioRepositorio.save(usuario));

            log.info("registrarUsuario respuesta:".concat(usuarioRegistrado.toString()));
            return usuarioRegistrado;

        } catch (RuntimeException ex) {
            log.error("registrarUsuario: ".concat(ex.getMessage())
            );

            throw new RuntimeException(ex);
        }

    }

    @Override
    public Usuario asignarModulosUsuario(
            Integer codigoUsuario, RegistrarUsuarioPeticionDTO registrarUsuarioPeticionDTO) {
        Usuario usuario = usuarioRepositorio.findById(codigoUsuario).orElseThrow(
                () -> new NoFoundException(MensajesErrorEnum.DATOS_NO_ENCONTRADOS, HttpStatus.NOT_FOUND.value()));


        Set<SubModulo> subModulos = registrarUsuarioPeticionDTO.getSubModulos().stream()
                .map(modulo -> subModuloRepositorio.findById(modulo.getCodigoSubModulo()).orElseThrow(
                        () -> new NoFoundException(MensajesErrorEnum.DATOS_NO_ENCONTRADOS, HttpStatus.NOT_FOUND.value())
                )).collect(Collectors.toSet());

        usuario.setSubModulos(subModulos);

        return usuarioRepositorio.save(usuario);

    }

    @Override
    public Set<SubModulo> consultarUsuarioModulos(Integer codigoUsuario) {
        Usuario usuario = usuarioRepositorio.findById(codigoUsuario).
                orElseThrow(() -> new RequestException(
                        MensajesErrorEnum.DATOS_NO_ENCONTRADOS, HttpStatus.BAD_REQUEST.value()));
        /**
         * Pendiente poara arreglar si la lista llega vacia
         */
        try {

            Set<SubModulo> listSubModulos = usuario.getSubModulos();
            if (listSubModulos.isEmpty()) {
                log.info("consultarUsuariosModulos: La lista de modulos esta vacia para este usuario: " + usuario.getCodigoUsuario());
                throw new RuntimeException("" + listSubModulos);

            }

            log.info("consultarUsuariosModulos: " + listSubModulos);
            return listSubModulos;

        } catch (RuntimeException ex) {

            log.error("consultarUsuariosModulos: ".concat(ex.getMessage()));
            throw new RuntimeException(ex);

        }
    }

    @Transactional
    public SubModulo asignarUsuarioModulo(Integer codigoUsuario, Integer codigoModulo) {

        Usuario usuario = usuarioRepositorio.findById(codigoUsuario).orElseThrow(
                () -> new RequestException(MensajesErrorEnum.DATOS_NO_ENCONTRADOS, HttpStatus.BAD_REQUEST.value()));
        SubModulo subModulo = subModuloRepositorio.findById(codigoModulo).orElseThrow(
                () -> new RequestException(MensajesErrorEnum.DATOS_NO_ENCONTRADOS, HttpStatus.BAD_REQUEST.value()));

        try {


            if (usuario.getSubModulos().contains(subModulo)) {
                log.info("asignarUsuarioModuloo: " + subModulo.getDescripcion());
                throw new RequestException(MensajesErrorEnum.MODULO_DUPLICADO, HttpStatus.BAD_REQUEST.value());
            }

            usuario.getSubModulos().add(subModulo);

            usuarioRepositorio.save(usuario);

            log.info("asignarUsuarioModulo: " + subModulo.getDescripcion());

            return subModulo;

        } catch (RuntimeException ex) {

            log.error("Error al asignar un modulo al usuario: " + ex.getMessage());
            throw new RuntimeException(ex);
        }

    }


    @Override
    public Optional<Usuario> consultarUsuariosModulos(Integer codigoUsuario) {
        return Optional.empty();
    }


    @Override
    public Usuario quitarRutaUsuario(Integer codigoUsuario, Integer codigoRuta) {
        Usuario usuario = usuarioRepositorio.findById(codigoUsuario).orElseThrow(() -> new RequestException(
                MensajesErrorEnum.USUARIO_NO_ENCONTRADO, HttpStatus.NOT_FOUND.value()));
        Ruta ruta = rutaRepositorio.findById(codigoRuta).orElseThrow(() -> new RequestException(
                MensajesErrorEnum.RUTA_NO_ENCONTRADO, HttpStatus.NOT_FOUND.value()));
        try {
            usuario.getRutas().remove(ruta);
            return usuarioRepositorio.save(usuario);
        } catch (RuntimeException ex) {
            log.error("quitarRutas: ".concat(ex.getMessage()));
            throw new RuntimeException(ex);
        }

    }

    @Override
    public Set<Ruta> asignarRutasUsuario(
            Integer codigoUsuario, RegistrarUsuarioPeticionDTO registrarUsuarioPeticionDTO) {
        Usuario usuario = usuarioRepositorio.findById(codigoUsuario).orElseThrow(
                () -> new NoFoundException(MensajesErrorEnum.USUARIO_NO_ENCONTRADO, HttpStatus.NOT_FOUND.value()));


        Set<Ruta> rutas = registrarUsuarioPeticionDTO.getRutas().stream()
                .map(ruta -> rutaRepositorio.findById(ruta.getCodigoRuta()).orElseThrow(
                        () -> new NoFoundException(MensajesErrorEnum.RUTA_NO_ENCONTRADO, HttpStatus.NOT_FOUND.value())
                )).collect(Collectors.toSet());
        try {
            usuario.setRutas(rutas);
            usuarioRepositorio.save(usuario);
            return rutas;

        } catch (RuntimeException ex) {
            log.error("asignarRutas: ".concat(ex.getMessage()));
            throw new RuntimeException(ex);
        }


    }

    @Override
    public List<Ruta> traerUsuarioRuta(Integer codigoUsuario) {
        List<Ruta> rutas = usuarioRepositorio.findById(codigoUsuario).map(usuario -> new ArrayList<>(usuario.getRutas())).orElseThrow(() -> new RequestException(
                MensajesErrorEnum.USUARIO_NO_ENCONTRADO, HttpStatus.NOT_FOUND.value()));
//corregir
      Usuario usuarios = usuarioRepositorio.findById(codigoUsuario).orElseThrow(() -> new RequestException(
              MensajesErrorEnum.USUARIO_NO_ENCONTRADO, HttpStatus.NOT_FOUND.value()));
        System.out.println("rutas = " + usuarios.getRutas());
        try {

            return rutas;

        } catch (RuntimeException ex) {
            log.error("traerUsuarioRuta: ".concat(ex.getMessage()));
            throw new RuntimeException(ex);
        }


    }
}
