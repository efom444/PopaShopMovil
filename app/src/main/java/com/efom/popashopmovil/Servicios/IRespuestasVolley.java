package com.efom.popashopmovil.Servicios;

//interfaz fachada
public interface IRespuestasVolley {
    /******* PETICIÓN EFECTIVA ******/
    void completeSuccess(Object object);

    /******* CONEXION FALLIDA ******/
    void completeError(Object object);
}
