package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Ruta;
import com.business.ventas.utils.IpadreRepository;

import java.util.List;

public interface RutaRepository extends IpadreRepository {

    RutaRepository listarRutas(Context context);

    RutaRepository setOnRespuestaSucces(RespuestaSucces<List<Ruta>> listen);

    RutaRepository setOnRespuestaError(RespuestaError listen);

}
