package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Orden;
import com.business.ventas.utils.IpadreRepository;

import java.util.List;

public interface OrdenesRepository extends IpadreRepository {

    OrdenesRepository listarOrdenes(Context context);

    OrdenesRepository detalleOrden(Context context, String codigoOrden);

    OrdenesRepository setOnRespuestaSucces(RespuestaSucces<?> listen);

    OrdenesRepository setOnRespuestaError(RespuestaError listen);

    void crearOrden(Context context, Orden orden, RespuestaSucces<Orden> succes, RespuestaError error);

    void eliminarOrden(Context context, Orden orden, RespuestaSucces<String> succes, RespuestaError error);

}
