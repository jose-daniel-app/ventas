package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.R;
import com.business.ventas.beans.Comprobante;
import com.business.ventas.beans.Factura;
import com.business.ventas.beans.Guia;
import com.business.ventas.utils.Fechas;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;


public class ComprobanteRepositoryImpl extends PadreRepository implements ComprobanteRepository {

    VentasLog log = LogFactory.createInstance().setTag(ComprobanteRepositoryImpl.class.getSimpleName());

    @Override
    public void listarComprovantes(Context context, RespuestaSucces<Lista<Comprobante>> succes, RespuestaError error) {

        log.info("solicitando los comprovantes al api rest");

        String columnas = "\"name,customer_name,grand_total,status,posting_date,modified,posting_time,creation\"";

        getService(context).listarFacturas(columnas).enqueue(new PadreRepository.CallRespuesta().listenRespuesta(resp1 -> {

            Lista<Comprobante> comprobantes = new Lista<>();

            recorrerLista(resp1.body().get("data").getAsJsonArray().iterator(), (item) -> {
                Factura factura = new Factura();
                factura.setFoto(R.drawable.ic_account_circle_black_24dp);
                factura.setCodigo(getString(item.get("name")));
                factura.setPagoTotal(getDouble(item.get("grand_total")));
                factura.setNombre(getString(item.get("customer_name")));
                factura.setFecha(getString(item.get("posting_date")));
                factura.setFechaPublicacion(Fechas.asDate(getString(item.get("posting_date"))));
                factura.setNombre(getString(item.get("customer_name")));
                comprobantes.add(factura);
            });

            getService(context).listarGuias(columnas).enqueue(new PadreRepository.CallRespuesta().listenRespuesta(resp2 -> {
                recorrerLista(resp2.body().get("data").getAsJsonArray().iterator(), (item) -> {
                    Guia guia = new Guia();
                    guia.setFoto(R.drawable.ic_account_circle_black_24dp);
                    guia.setCodigo(getString(item.get("name")));
                    guia.setPagoTotal(getDouble(item.get("grand_total")));
                    guia.setNombre(getString(item.get("customer_name")));
                    guia.setFecha(getString(item.get("posting_date")));
                    guia.setFechaPublicacion(Fechas.asDate(getString(item.get("posting_date"))));
                    guia.setNombre(getString(item.get("customer_name")));
                    comprobantes.add(guia);
                });
                succes.onRespuestaSucces(comprobantes);

            }).listenError(error::onRespuestaError));

        }).listenError(error::onRespuestaError));

    }
}
