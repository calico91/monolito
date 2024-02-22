package com.playtech.listo.servicio.ruta;

import com.playtech.listo.controlador.ruta.dto.RegistrarRutaPeticionDTO;
import com.playtech.listo.controlador.ruta.dto.RegistrarRutaRespuestaDTO;
import com.playtech.listo.excepciones.NoFoundException;
import com.playtech.listo.excepciones.RequestException;
import com.playtech.listo.mapper.RutaMapper;
import com.playtech.listo.modelo.Ruta;
import com.playtech.listo.repositorio.RutaRepositorio;
import com.playtech.listo.utils.MensajesErrorEnum;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RutaServiceImpl implements RutaService {

    private final RutaMapper rutaMapper;

    private final RutaRepositorio rutaRepositorio;

    @PostConstruct
    public void init() {
        insertarDatos();
    }

    @Override
    public RegistrarRutaRespuestaDTO registrarRutaDto(RegistrarRutaPeticionDTO registrarRutaPeticionDTO) {
        try {
            Ruta registroRuta = rutaMapper.registrarRutaRespuestaDTOToRuta(registrarRutaPeticionDTO);

            registroRuta.setEstado("A");

            registroRuta = rutaRepositorio.save(registroRuta);

            return rutaMapper.rutaToRegistrarRutaRespuestaDTO(registroRuta);
        } catch (RuntimeException ex) {
            log.error("registrarRuta: ".concat(ex.getMessage())
            );

            throw new RuntimeException(ex);
        }

    }

    @Override
    public List<Ruta> traerTodasRutas() {
        List<Ruta> rutas = rutaRepositorio.findAll();
        try {
            if (rutas.isEmpty()) {
                log.info("consultarRutas: " + rutas);
                throw new RequestException(MensajesErrorEnum.RUTA_NO_ENCONTRADO, HttpStatus.BAD_REQUEST.value());
                //ColocarMensaje

            }
            return rutas;
        } catch (RuntimeException ex) {
            log.error("consultarRutas: ".concat(ex.getMessage()));
            throw new RuntimeException(ex);
        }

    }

    @Override
    public Ruta traerRutasId(Integer id) {
        Ruta ruta = rutaRepositorio.findById(id).orElseThrow(
                () -> new RequestException(MensajesErrorEnum.RUTA_NO_ENCONTRADO, HttpStatus.BAD_REQUEST.value()));
        try {

            return ruta;

        } catch (RuntimeException ex) {
            log.error("consultarRutas: ".concat(ex.getMessage()));
            throw new RuntimeException(ex);
        }


    }


    @Override
    public RegistrarRutaRespuestaDTO actualizarRutasId(Integer codigoRuta, RegistrarRutaPeticionDTO registrarRutaPeticionDTO) {
        Ruta  rutaOpt = rutaRepositorio.findById(codigoRuta).orElseThrow(() -> new NoFoundException(MensajesErrorEnum.RUTA_NO_ENCONTRADO, HttpStatus.NOT_FOUND.value()));
        try {
            if (rutaOpt.getCodigoRuta().equals(codigoRuta)) {

                Ruta actualizoRuta = rutaMapper.registrarRutaRespuestaDTOToRuta(registrarRutaPeticionDTO);

                rutaOpt.setDescripcion(actualizoRuta.getDescripcion());
                rutaOpt.setCelular(actualizoRuta.getCelular());
                rutaOpt.setEstado(actualizoRuta.getEstado());

               Ruta rutas = rutaRepositorio.save(rutaOpt);


                return rutaMapper.rutaToRegistrarRutaRespuestaDTO(rutas);

            }

            throw new RequestException(
                    MensajesErrorEnum.RUTA_NO_ACTUALIZADA, HttpStatus.BAD_REQUEST.value());

        } catch (RuntimeException ex) {
            log.error("actualizarRutas: ".concat(ex.getMessage()));
            throw new RuntimeException(ex);
        }


    }

    @Override
    public Ruta inactivarRuta(Integer codigoRuta, String estado) {
        Ruta ruta = rutaRepositorio.findById(codigoRuta).orElseThrow(
                () -> new NoFoundException(MensajesErrorEnum.DATOS_NO_ENCONTRADOS, HttpStatus.NOT_FOUND.value()));
        try {
            ruta.setEstado(estado);
            return rutaRepositorio.save(ruta);

        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        }


    }


    private void insertarDatos() {
        List<Ruta> agregarRutas = rutaRepositorio.findAll();

        if (agregarRutas.isEmpty()) {

            List<Ruta> rutasList = new ArrayList<>();
            rutasList.add(new Ruta(1, "ruta No1", "312000000", "A"));

            rutasList.add(new Ruta(2, "ruta No2", "3230000000", "A"));
            rutaRepositorio.saveAll(rutasList);

        }
        rutaRepositorio.saveAll(agregarRutas);

    }


}
