package com.playtech.listo.controlador.ruta;

import com.playtech.listo.controlador.ruta.dto.RegistrarRutaPeticionDTO;
import com.playtech.listo.controlador.ruta.dto.RegistrarRutaRespuestaDTO;
import com.playtech.listo.modelo.Ruta;
import com.playtech.listo.servicio.ruta.RutaServiceImpl;
import com.playtech.listo.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Ruta")
public class RutaControlador {


    private final RutaServiceImpl rutaService;

    @GetMapping("/traerRutas")
    public ResponseEntity<Object> traerRutas()  {
        return new ResponseHandler().generateResponse(
                HttpStatus.OK, rutaService.traerTodasRutas());

    }

    @GetMapping("/traerRuta/{id}")
    public ResponseEntity<Object> traerRutasId(@PathVariable Integer id)  {

        return new ResponseHandler().generateResponse(
                HttpStatus.OK, rutaService.traerRutasId(id));

    }


    @PostMapping("/registrarRuta")
    public ResponseEntity<Object> registrarRutas(@RequestBody RegistrarRutaPeticionDTO ruta) {
        return new ResponseHandler().generateResponse(
                HttpStatus.OK, rutaService.registrarRutaDto(ruta));
    }

    @PutMapping("/actualizarRuta/{id}")
    public ResponseEntity<Object> actualizarRutas(@PathVariable Integer id, @RequestBody RegistrarRutaPeticionDTO ruta) {
        return new ResponseHandler().generateResponse(
                HttpStatus.OK, rutaService.actualizarRutasId(id, ruta));
    }
    @PutMapping("/inactivarRuta/{id}/{estado}")
    public ResponseEntity<Object> inactivarRutas(@PathVariable Integer id,@PathVariable String estado ) {
        return new ResponseHandler().generateResponse(
                HttpStatus.OK, rutaService.inactivarRuta(id, estado));
    }

}
