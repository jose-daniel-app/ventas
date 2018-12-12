package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Orden;

import java.util.List;

public interface OrdenesRepository {

    OrdenesRepository listarOrdenes(Context context);

    OrdenesRepository setOnRespuestaSucces(RespuestaSucces<List<Orden>> listen);

    OrdenesRepository setOnRespuestaError(RespuestaError listen);

    @FunctionalInterface
    interface RespuestaSucces<T> {
        void onRespuestaSucces(T t);
    }

    @FunctionalInterface
    interface RespuestaError {
        void onRespuestaError(String mensaje);
    }

}
