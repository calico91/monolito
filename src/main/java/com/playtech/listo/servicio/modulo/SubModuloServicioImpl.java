package com.playtech.listo.servicio.modulo;

import com.playtech.listo.controlador.modulo.dto.SubModuloDTO;
import com.playtech.listo.excepciones.RequestException;
import com.playtech.listo.mapper.SubModuloMapper;
import com.playtech.listo.modelo.SubModulo;
import com.playtech.listo.repositorio.SubModuloRepositorio;
import com.playtech.listo.utils.MensajesErrorEnum;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@AllArgsConstructor
@Service
@Slf4j
public class SubModuloServicioImpl implements SubModuloServicio {

    SubModuloRepositorio subModuloRepositorio;

    private final SubModuloMapper subModuloMapper;

    @PostConstruct
    public void init() {
        insertarDatos();
    }

    @Override
    public SubModuloDTO consultarSubModulo(Integer codigoSubModulo) {
        SubModulo consultarSubModulo = subModuloRepositorio.findById(codigoSubModulo).orElseThrow(
                () -> new RequestException(MensajesErrorEnum.MODULO_NO_ENCONTRADO, HttpStatus.NOT_FOUND.value()));

        SubModuloDTO subModuloDTO = subModuloMapper.subModuloToSubModuloDTO(consultarSubModulo);

        try {
            log.info("consultarCodigo: " + consultarSubModulo.getCodigoSubModulo());
            log.info("consultarModulo: " + consultarSubModulo.getDescripcion());
            return subModuloDTO;

        }catch (RuntimeException ex){
            log.error("consultarModulo: ".concat(ex.getMessage()));
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<SubModuloDTO> consultarSubModulos() {
        List<SubModulo> subModulos = subModuloRepositorio.findAll();

        List<SubModuloDTO> subModuloDTOList = subModuloMapper.subModuloListToSubModuloDTOList(subModulos);

        try {
            if (subModulos.isEmpty()) {
                log.info("consultarModulos: " + subModuloDTOList);
                throw new RequestException(MensajesErrorEnum.MODULO_DUPLICADO, HttpStatus.NOT_FOUND.value());
            }

            log.info("consultarModulos: " + subModuloDTOList);

            return subModuloDTOList;

        } catch (RuntimeException ex) {

            log.error("consultarModulos: ".concat(ex.getMessage()));
            throw new RuntimeException(ex);

        }
    }

    private void insertarDatos() {


        List<SubModulo> subModuloList = new ArrayList<>();
        subModuloList.add(new SubModulo(100001, "crearUsuario", "A"));

        subModuloList.add(new SubModulo(100002, "consultarUsuario", "A"));
        subModuloRepositorio.saveAll(subModuloList);

    }
}
