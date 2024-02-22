package com.playtech.listo.controlador.login.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginPeticionDTO {

    String login;
    String clave;
}
