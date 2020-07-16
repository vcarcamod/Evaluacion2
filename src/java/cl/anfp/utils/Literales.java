/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.utils;

import cl.anfp.utilitarios.StringUtils;

/**
 *
 * @author exvicad
 */
public enum Literales {
    URL_HOME("./"),
    URL_LOGIN("./login.jsp"),
    URL_REGISTRO("./registro_usuario.jsp"),
    URL_SITIO_PRIVADO("webPrivado/"),
    URL_SITIO_PRIVADO_ADMIN_REG("webPrivado/registro.jsp"),
    MSJ_ERROR_LOGIN_PWD("La contraseña ingresada es incorrecta, por favor reintente. "),
    MSJ_ERROR_LOGIN_USR("El usuario ingresado no está registrado. Por favor reintente. "),
    MSJ_ERROR_LOGIN_GENERICO("Ocurrió un error al intentar acceder al sistema. "),
    MSJ_REGISTRO_OK("Usuario registrado correctamente. "),
    MSJ_REGISTRO_DATOS_OK("Informacion registrada correctamente. "),
    MSJ_REGISTRO_DATOS_EXISTE("El dato ingresado ya existe. "),
    MSJ_REGISTRO_NOK_DATOS("Todos los datos son requeridos. "),
    MSJ_CAMPO_REQUERIDO("Campo requerido. "),
    MSJ_REGISTRO_NOK_USUARIO_EXISTE("Usuario ya existe. "),
    MSJ_REGISTRO_NOK_GENERICO("Ocurrio un error inesperado. "),
    MSJ_ACCESO_RESTRINGIDO("Acceso restringido. "),
    MSJ_ACCION_INCORRECTA("Acción no permitida. ");
    
    private final String literal;
    
    Literales(String lit){
        this.literal = lit;
    }
    
    public String getLiteral(){
        return StringUtils.encoding(literal);
    }
    
}
