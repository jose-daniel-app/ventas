package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Producto;
import com.business.ventas.beans.Requerimiento;
import com.business.ventas.beans.Ruta;
import com.business.ventas.utils.Fechas;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class RequerimientosRepositoryImpl extends PadreRepository implements RequerimientosRepository {

    VentasLog log = LogFactory.createInstance().setTag(RequerimientosRepositoryImpl.class.getSimpleName());

    @Override
    public void listarRequerimientos(Context context, RespuestaSucces<List<Requerimiento>> succes, RespuestaError error) {
        log.info("solicitando la listarRequerimientos al api rest");
        String columnas = "\"status,name,transaction_date,title,schedule_date,company\"";
        getService(context).listarRequerimientos(columnas).enqueue(new PadreRepository.CallRespuesta().listenRespuesta(resp -> {

            Lista<Requerimiento> lista = new Lista<>();
            recorrerLista(resp.body().get("data").getAsJsonArray().iterator(), (item) -> {
                Requerimiento rq = new Requerimiento();
                rq.setName(getString(item.get("name")));
                rq.setStatus(getString(item.get("status")));
                rq.setTitle(getString(item.get("title")));
                rq.setTransactionDate(Fechas.asDate(getString(item.get("transaction_date"))));
                rq.setScheduleDate(Fechas.asDate(getString(item.get("schedule_date"))));
                rq.setCompany(getString(item.get("company")));
                lista.agregar(rq);
            });
            succes.onRespuestaSucces(lista);

        }).listenError(error::onRespuestaError));
    }

    @Override
    public void obtenerRequerimiento(Context context, Requerimiento requerimiento, RespuestaSucces<Requerimiento> succes, RespuestaError error) {
        getService(context).obtenerDetalleRequerimiento(requerimiento.getName())
                .enqueue(new PadreRepository.CallRespuesta().listenRespuesta(resp -> {

                    JsonObject data = resp.body().get("data").getAsJsonObject();
                    //requerimiento.setWarehouse(getString(data.get("warehouse")));

                    List<Producto> lista = new ArrayList<>();
                    recorrerLista(data.get("items").getAsJsonArray().iterator(), (item) -> {
                        Producto itemRq = new Producto();
                        itemRq.setItemCode(getString(item.get("item_code")));
                        itemRq.setNombre(getString(item.get("item_name")));
                        itemRq.setPrecioUnitario(getDouble(item.get("rate")));
                        itemRq.setCantidad(getInt(item.get("qty")));
                        itemRq.setAlmacen(getString(item.get("warehouse")));
                        lista.add(itemRq);
                    });
                    requerimiento.setRuta(new Ruta(){{setCodRuta(lista.get(0).getAlmacen());}});
                    requerimiento.setItems(lista);
                    succes.onRespuestaSucces(requerimiento);
                })
                        .listenError(error::onRespuestaError));
    }

    @Override
    public void crearRequerimiento(Context context, Requerimiento requerimiento, RespuestaSucces<Requerimiento> succes, RespuestaError error) {
        JsonObject object = new JsonObject();
        object.addProperty("transaction_date",
                Fechas.darFormatoALaFecha("yyyy-MM-dd", requerimiento.getTransactionDate()));
        object.addProperty("schedule_date",
                Fechas.darFormatoALaFecha("yyyy-MM-dd", requerimiento.getScheduleDate()));
        object.addProperty("company", requerimiento.getRuta().getCompany());

        JsonArray listaJson = new JsonArray();
        new Lista<Producto>(requerimiento.getItems()).foreach(producto -> {
            JsonObject item = new JsonObject();
            item.addProperty("item_code", producto.getItemCode());
            item.addProperty("qty", producto.getCantidad());
            item.addProperty("warehouse", requerimiento.getRuta().getCodRuta());
            listaJson.add(item);
        });
        log.info("el almacen que se esta enviando %s",requerimiento.getRuta().getDirRuta());
        object.add("items", listaJson);

        getService(context).crearRequerimiento(object).enqueue(
                new PadreRepository.CallRespuesta().listenRespuesta(responseOk -> {

                    JsonObject data = responseOk.body().get("data").getAsJsonObject();
                    Requerimiento rq = new Requerimiento();
                    rq.setName(getString(data.get("name")));
                    rq.setStatus(getString(data.get("status")));
                    rq.setTitle(getString(data.get("title")));
                    rq.setTransactionDate(Fechas.asDate(getString(data.get("transaction_date"))));
                    rq.setScheduleDate(Fechas.asDate(getString(data.get("schedule_date"))));
                    rq.setCompany(getString(data.get("company")));
                    Lista<Producto> productos = new Lista<>();

                    recorrerLista(data.get("items").getAsJsonArray().iterator(), (item) -> {
                        Producto producto = new Producto();
                        producto.setItemCode(getString(item.get("item_code")));
                        producto.setCantidad(getInt(item.get("qty")));
                        producto.setPrecioUnitario(getDouble(item.get("rate")));
                        producto.setNombre(getString(item.get("description")));
                        //producto.setPrecioCantidad(getDouble(item.get("base_amount")));
                        productos.add(producto);
                    });
                    rq.setItems(productos);
                    succes.onRespuestaSucces(rq);
                })
                        .listenError(error::onRespuestaError)
        );
    }

    @Override
    public void actualizarRequerimiento(Context context, Requerimiento requerimiento, RespuestaSucces<Requerimiento> succes, RespuestaError error) {

        JsonObject object = new JsonObject();
        JsonArray listaJson = new JsonArray();
        new Lista<Producto>(requerimiento.getItems()).foreach(producto -> {
            JsonObject item = new JsonObject();
            item.addProperty("item_code", producto.getItemCode());
            item.addProperty("qty", producto.getCantidad());
            item.addProperty("warehouse", requerimiento.getRuta().getCodRuta());
            listaJson.add(item);
        });
        object.add("items", listaJson);

        getService(context).actualizarRequerimiento(requerimiento.getName(), object).enqueue(
                new PadreRepository.CallRespuesta().listenRespuesta(resp -> {
                    succes.onRespuestaSucces(requerimiento);
                }).listenError(error::onRespuestaError));
    }
}
