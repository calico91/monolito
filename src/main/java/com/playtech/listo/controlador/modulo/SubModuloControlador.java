package com.playtech.listo.controlador.modulo;

import com.playtech.listo.servicio.modulo.SubModuloServicio;
import com.playtech.listo.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modulo")
@AllArgsConstructor
public class SubModuloControlador {


    private final SubModuloServicio subModuloServicio;

    @GetMapping("/consultarSubModulo/{id}")
    public ResponseEntity<Object> consultarSubModulo(@PathVariable Integer id) {
        return new ResponseHandler().generateResponse(HttpStatus.OK, subModuloServicio.consultarSubModulo(id));
    }

    @GetMapping("/consultarSubModulos")
    public ResponseEntity<Object> consultarSubModulos() {
        return new ResponseHandler().generateResponse(HttpStatus.OK, subModuloServicio.consultarSubModulos());
    }

}
