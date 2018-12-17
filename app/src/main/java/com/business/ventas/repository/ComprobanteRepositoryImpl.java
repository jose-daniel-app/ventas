package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Comprobante;
import com.business.ventas.beans.Factura;
import com.business.ventas.beans.Guia;
import com.business.ventas.utils.Fechas;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.google.gson.JsonObject;


public class ComprobanteRepositoryImpl extends PadreRepository implements ComprobanteRepository {

    VentasLog log = LogFactory.createInstance().setTag(ComprobanteRepositoryImpl.class.getSimpleName());

    @Override
    public void listarComprovantes(Context context, RespuestaSucces<Lista<Comprobante>> succes, RespuestaError error) {
        String columnas = "\"name,customer_name,grand_total,status,posting_date,modified,posting_time,creation\"";

        getService(context).listarFacturas(columnas).enqueue(new PadreRepository.CallRespuesta().listenRespuesta(resp1 -> {
            Lista<Comprobante> comprobantes = new Lista<>();
            if (resp1.code() == SUCCES) {
                //JsonObject data1 = resp1.body().get("data").getAsJsonObject();
                recorrerLista(resp1.body().get("data").getAsJsonArray().iterator(), (item) -> {
                    Factura factura = new Factura();
                    factura.setCodigo(getString(item.get("name")));
                    factura.setPagoTotal(getDouble(item.get("grand_total")));
                    factura.setNombre(getString(item.get("customer_name")));
                    factura.setFecha(getString(item.get("posting_date")));
                    factura.setFechaPublicacion(Fechas.asDate(getString(item.get("posting_date"))));
                    factura.setCodigo(getString(item.get("customer_name")));
                    comprobantes.add(factura);
                });

                getService(context).listarGuias(columnas).enqueue(new PadreRepository.CallRespuesta().listenRespuesta(resp2 -> {
                    if (resp2.code() == SUCCES) {
                        recorrerLista(resp1.body().get("data").getAsJsonArray().iterator(), (item) -> {
                            Guia guia = new Guia();
                            guia.setCodigo(getString(item.get("name")));
                            guia.setPagoTotal(getDouble(item.get("grand_total")));
                            guia.setNombre(getString(item.get("customer_name")));
                            guia.setFecha(getString(item.get("posting_date")));
                            guia.setFechaPublicacion(Fechas.asDate(getString(item.get("posting_date"))));
                            guia.setCodigo(getString(item.get("customer_name")));
                            comprobantes.add(guia);
                        });
                    } else {
                        error.onRespuestaError("codigo de respuesta " + resp2.code());
                    }
                    succes.onRespuestaSucces(comprobantes);
                }));

            } else {
                error.onRespuestaError("codigo de respuesta " + resp1.code());
            }

        }));

    }
}
