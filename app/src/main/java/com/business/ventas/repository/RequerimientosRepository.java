package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Requerimiento;
import com.business.ventas.utils.IpadreRepository;

import java.util.List;

public interface RequerimientosRepository extends IpadreRepository {

    void listarRequerimientos(Context context, RespuestaSucces<List<Requerimiento>> succes, RespuestaError error);

    void obtenerRequerimiento(Context context, Requerimiento requerimiento, RespuestaSucces<Requerimiento> succes, RespuestaError error);
}
