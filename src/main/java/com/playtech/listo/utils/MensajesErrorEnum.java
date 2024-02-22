package com.playtech.listo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum MensajesErrorEnum {

    USUARIO_REGISTRADO(
            "1",
            "Usuario ya se encuentra registrado",
            "Verifique el nombre de usuario que se encuentra creando"),
    DATOS_NO_ENCONTRADOS(
            "2",
            "No se encontraron datos con la informacion suministrada",
            "Verifique los parametros de busqueda e intente de nuevo"),
    CAMPO_NULO(
            "3",
            "El campo no puede ser nulo",
            "Verifique la informacion ingresada"),
    ACCESO_DENEGADO_POR_PERMISOS(
            "4",
            "No tiene permisos para este modulo",
            "Verifique los permisos asignados"),
    LOGIN_INCORRRECTO(
            "50",
            "Usuario o contraseña incorrectos",
            "Verifique la informacion ingresada"),

    FORMATO_PETICION_INCORRECTO(
            "50",
            "El formato JSON se encuentra incorrecto",
            "Verifique la informacion ingresada"),

    JWT_INVALIDO(
            "50",
            "El token de autenticacion es invalido",
            "Realice nuevamente autenticacion"),
    TOKEN_EXPIRO(
            "50",
            "El token expiro",
            "Realice nuevamente autenticacion"),

    MODULO_DUPLICADO(
            "3",
            "El modulo ya se encuentra asignado",
            "Verifique los modulos asignados al usuario"),
    MODULO_NO_ENCONTRADO(
            "3",
            "El modulo no se encuentra en la base de datos",
            "Verifique los modulos"),
    ERROR_EN_PARAMETRO(
            "3",
            "Estado de modulo sin cambios",
            "Verifique los parametros"),

    USUARIO_NO_ENCONTRADO(
            "2",
            "Usuario no fue encontrado",
            "Verifique el nombre del usuario"),

    RUTA_NO_ENCONTRADO(
            "2",
            "Ruta no fue encontrada",
            "Verifique el nombre de la ruta"),

    RUTA_NO_ACTUALIZADA(
            "2",
            "Ruta no pudo ser actualizada",
            "Verifique los datos de la ruta"),

    RUTA_NO_INACTIVA(
            "2",
            "Ruta no pudo ser inactivada",
            "Por favor contactese con el administrador"),

    ERROR_PARAMETRO_RUTA(
            "2",
            "Estado de la ruta sin cambios",
            "Verifique los parametros"),

    RUTA_YA_ASIGNADA(
            "2",
            "la ruta ya está asignado al usuario",
            "Verifique los datos de la ruta"),

    RUTA_YA_DESIGNADA(
            "2",
            "la ruta ya no lo tiene el usuario",
            "Verifique los datos de la ruta"),


    ERROR_ASIGNAR_RUTA(
            "2",
            "No se puede asignar una ruta inactiva a un usuario",
            "Verifique los datos de la ruta"),

    RUTA_SIN_RESULTADO(
            "2",
            "    Usuario o ruta no encontrado",
            "Verifique los datos de la ruta"),

    RUTA_REGISTRADO(
            "2",
            "Ruta ya se encuentra registrado",
            "Verifique el nombre de Ruta que se encuentra creando ");

    private final String codigo;
    private final String mensajeError;
    private final String recomendacion;


}

