package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Cliente;

import java.util.List;

public interface ClientesRepository {

    ClientesRepository listarClientes(Context context);

    ClientesRepository setOnRespuestaSucces(RespuestaSucces<List<Cliente>> listen);

    ClientesRepository setOnRespuestaError(RespuestaError listen);

    @FunctionalInterface
    interface RespuestaSucces<T> {
        void onRespuestaSucces(T t);
    }

    @FunctionalInterface
    interface RespuestaError {
        void onRespuestaError(String mensaje);
    }
}
