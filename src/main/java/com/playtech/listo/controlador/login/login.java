package com.playtech.listo.controlador.login;

import com.playtech.listo.controlador.login.dto.LoginPeticionDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class login {


    /**
     * @param
     * @
     */
    @PostMapping
    public void login(@RequestBody LoginPeticionDTO loginPeticionDTO) {
    }


}
