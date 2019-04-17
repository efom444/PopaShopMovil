package com.efom.popashopmovil.Servicios;

//interfaz fachada
public interface ComunicaVolley {
    /******* SENTENCIA EFECTIVA ******/
    void completeSuccess(Object object);
    /******* CONEXION FALLIDA ******/
    void completeError(Object object);
}
