package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Producto;

import java.util.List;

public interface ProductosRepository {

    ProductosRepository listarProductos(Context context);
    ProductosRepository setOnRespuestaSucces(RespuestaSucces<List<Producto>> listen);
    ProductosRepository setOnRespuestaError(RespuestaError listen);

    @FunctionalInterface
    interface RespuestaSucces<T> {
        void onRespuestaSucces(T t);
    }

    @FunctionalInterface
    interface RespuestaError {
        void onRespuestaError(String mensaje);
    }
}
