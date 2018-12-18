package com.business.ventas.utils;

public interface IpadreRepository  {

    @FunctionalInterface
    interface RespuestaSucces<T> {
        void onRespuestaSucces(T t);
    }

    @FunctionalInterface
    interface RespuestaError {
        void onRespuestaError(String mensaje);
    }

}
