package com.playtech.listo.repositorio.submodulo;

import com.playtech.listo.modelo.SubModulo;
import com.playtech.listo.repositorio.SubModuloRepositorio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubModuloRepositoryTests {

    @Autowired
    private SubModuloRepositorio subModuloRepositorio;

    private final static Logger log = LoggerFactory.getLogger(SubModuloRepositorio.class);

    private final static int MODULOID = 100003;
    private final static String MESSAGE = "Prueba";

    @Test
    @Transactional
    @Order(1)
    void save() {

        log.info(MESSAGE);

        Optional<SubModulo> moduloOptional = subModuloRepositorio.findById(MODULOID);

        log.info("log: " + MODULOID);

        assertFalse(moduloOptional.isPresent(), "El modulo con id: " + MODULOID + " ya existe.");

        SubModulo subModulo = new SubModulo();
        subModulo.setCodigoSubModulo(100003);
        subModulo.setDescripcion("Guardar Clientes");
        subModulo.setEstado("A");

        subModuloRepositorio.save(subModulo);
    }

    @Test
    @Transactional
    @Order(2)
    void findById() {
        log.info(MESSAGE);

        Optional<SubModulo> moduloOptional = subModuloRepositorio.findById(MODULOID);

        assertTrue(moduloOptional.isPresent(),
                "El modulo con id: " + MODULOID + " no existe.");
    }

    @Test
    @Transactional
    @Order(3)
    void update() {
        log.info(MESSAGE);

        Optional<SubModulo> moduloOptional = subModuloRepositorio.findById(MODULOID);

        assertTrue(moduloOptional.isPresent(), "El modulo con id: " + MODULOID + " no existe.");

        SubModulo subModulo = moduloOptional.get();
        subModulo.setEstado("B");

        subModuloRepositorio.save(subModulo);
    }

    @Test
    @Transactional
    @Order(4)
    void findAll() {
        log.info(MESSAGE);

        List<SubModulo> subModuloList = subModuloRepositorio.findAll();

        assertFalse(subModuloList.isEmpty(), "No hay módulos encontrados");

        subModuloRepositorio.findAll().forEach(modulo -> {
            log.info("Codigo modulo: " +modulo.getCodigoSubModulo());
            log.info("Descripcion: " + modulo.getDescripcion());
        });
    }

    @Test
    @Transactional
    @Order(5)
    void delete() {
        log.info(MESSAGE);

        Optional<SubModulo> moduloOptional = subModuloRepositorio.findById(MODULOID);

        assertTrue(moduloOptional.isPresent(), "El modulo con id: " + MODULOID + " no existe.");

        SubModulo subModulo = moduloOptional.get();

        subModuloRepositorio.delete(subModulo);

    }
}
