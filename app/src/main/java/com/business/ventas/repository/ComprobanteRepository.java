package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Comprobante;
import com.business.ventas.utils.IpadreRepository;
import com.business.ventas.utils.Lista;

public interface ComprobanteRepository extends IpadreRepository {

    void listarComprovantes(Context context, RespuestaSucces<Lista<Comprobante>> succes, RespuestaError error);

    void detalleDeComprobante(Context context, String codigo, int tipoComprobante, RespuestaSucces<Comprobante> succes, RespuestaError error);

}
