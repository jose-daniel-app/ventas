package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Orden;
import com.business.ventas.utils.IpadreRepository;

public interface AlmacenRepository extends IpadreRepository {

    void obtenerAlmacen(Context context, String territorio, RespuestaSucces<String> succes, RespuestaError error);

}
