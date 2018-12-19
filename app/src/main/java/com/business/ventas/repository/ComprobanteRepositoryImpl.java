package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.R;
import com.business.ventas.apiRest.Service;
import com.business.ventas.beans.Comprobante;
import com.business.ventas.beans.Factura;
import com.business.ventas.beans.Guia;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.Fechas;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.google.gson.JsonObject;



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
    /*public void listarComprovantes(Context context, RespuestaSucces<Lista<Comprobante>> succes, RespuestaError error){

        String columnas = "\"name,customer_name,grand_total,status,posting_date,modified,posting_time,creation\"";
        final Disposable subscribe = Observable.zip(
                getService(context).listarFacturas1(columnas),
                getService(context).listarGuias1(columnas),
                (resp1, resp2) -> {

                    Lista<Comprobante> comprobantes = new Lista<>();
                    recorrerLista(resp1.body().get("data").getAsJsonArray().iterator(), (item) -> {
                        Factura factura = new Factura();
                        factura.setCodigo(getString(item.get("name")));
                        factura.setPagoTotal(getDouble(item.get("grand_total")));
                        factura.setNombre(getString(item.get("customer_name")));
                        factura.setFecha(getString(item.get("posting_date")));
                        factura.setFechaPublicacion(Fechas.asDate(getString(item.get("posting_date"))));
                        factura.setNombre(getString(item.get("customer_name")));
                        comprobantes.add(factura);
                    });

                    recorrerLista(resp2.body().get("data").getAsJsonArray().iterator(), (item) -> {
                        Guia guia = new Guia();
                        guia.setCodigo(getString(item.get("name")));
                        guia.setPagoTotal(getDouble(item.get("grand_total")));
                        guia.setNombre(getString(item.get("customer_name")));
                        guia.setFecha(getString(item.get("posting_date")));
                        guia.setFechaPublicacion(Fechas.asDate(getString(item.get("posting_date"))));
                        guia.setNombre(getString(item.get("customer_name")));
                        comprobantes.add(guia);
                    });

                    return comprobantes;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(succes::onRespuestaSucces);
    }*/

    @Override
    public void detalleDeComprobante(Context context, String codigo, int tipoComprobante, RespuestaSucces<Comprobante> succes, RespuestaError error) {

        log.info("solicitando el detalle del comprobante al api rest..");
        Service service = getService(context);
        if (tipoComprobante == Comprobante.FACTURA) {
            service.obtenerDetalleFactura(codigo).enqueue(new PadreRepository.CallRespuesta().listenRespuesta(resp -> {
                JsonObject data = resp.body().get("data").getAsJsonObject();
                Factura factura = new Factura();
                factura.setCodigo(getString(data.get("name")));
                factura.setPagoTotal(getDouble(data.get("grand_total")));
                factura.setNombre(getString(data.get("customer_name")));
                factura.setFecha(getString(data.get("posting_date")));
                factura.setFechaPublicacion(Fechas.asDate(getString(data.get("posting_date"))));
                factura.setNombre(getString(data.get("customer_name")));
                recorrerLista(data.get("items").getAsJsonArray().iterator(), (item) -> {
                    Producto producto = new Producto();
                    producto.setItemCode(getString(item.get("item_code")));
                    producto.setCantidad(getInt(item.get("qty")));
                    producto.setPrecioUnitario(getDouble(item.get("rate")));
                    producto.setNombre(getString(item.get("description")));
                    producto.setPrecioCantidad(getDouble(item.get("base_amount")));
                    factura.getProductos().add(producto);
                });
                succes.onRespuestaSucces(factura);

            }).listenError(error::onRespuestaError));
        } else if (tipoComprobante == Comprobante.GUIA) {
            service.obtenerDetalleGuia(codigo).enqueue(new PadreRepository.CallRespuesta().listenRespuesta(resp -> {

                JsonObject data = resp.body().get("data").getAsJsonObject();
                Guia guia = new Guia();
                guia.setCodigo(getString(data.get("name")));
                guia.setPagoTotal(getDouble(data.get("grand_total")));
                guia.setNombre(getString(data.get("customer_name")));
                guia.setFecha(getString(data.get("posting_date")));
                guia.setFechaPublicacion(Fechas.asDate(getString(data.get("posting_date"))));
                guia.setNombre(getString(data.get("customer_name")));
                recorrerLista(data.get("items").getAsJsonArray().iterator(), (item) -> {
                    Producto producto = new Producto();
                    producto.setItemCode(getString(item.get("item_code")));
                    producto.setCantidad(getInt(item.get("qty")));
                    producto.setPrecioUnitario(getDouble(item.get("rate")));
                    producto.setNombre(getString(item.get("description")));
                    producto.setPrecioCantidad(getDouble(item.get("base_amount")));
                    guia.getProductos().add(producto);
                });
                succes.onRespuestaSucces(guia);

            }).listenError(error::onRespuestaError));
        }

    }
}
