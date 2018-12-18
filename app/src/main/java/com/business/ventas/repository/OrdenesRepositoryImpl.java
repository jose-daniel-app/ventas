package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Orden;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.Fechas;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdenesRepositoryImpl extends PadreRepository implements OrdenesRepository {

    VentasLog log = LogFactory.createInstance().setTag(OrdenesRepositoryImpl.class.getSimpleName());
    RespuestaSucces succes;
    RespuestaError error;

    @Override
    public OrdenesRepository listarOrdenes(Context context) {

        getService(context).listarOrdenes("\"name,customer_name,customer,customer_address,address_display,delivery_date,other_charges_calculation\"")
        .enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    Lista<Orden> ordenes = new Lista<>();
                    Iterator<JsonElement> iterar = response.body().getAsJsonArray("data").iterator();
                    while (iterar.hasNext()) {
                        JsonObject json = iterar.next().getAsJsonObject();
                        Orden orden = new Orden();
                        orden.setCodigo(json.get("name").isJsonNull() ? null : json.get("name").getAsString());
                        orden.setNombreCliente(json.get("customer_name").isJsonNull() ? null : json.get("customer_name").getAsString());
                        orden.setDirecionCliente(json.get("address_display").isJsonNull() ? null : json.get("address_display")
                                .getAsString().replaceAll("<br>"," "));
                        orden.setFechaEntrega(json.get("customer_name").isJsonNull() ? null : Fechas.asDate(json.get("delivery_date").getAsString()));
                        ordenes.add(orden);
                    }
                    succes.onRespuestaSucces(ordenes);
                } else {
                    error.onRespuestaError(String.format("error codigo %s", response.code()));
                }
            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                error.onRespuestaError(t.getMessage());
            }
        });

        return this;
    }

    @Override
    public OrdenesRepository detalleOrden(Context context, String codigoOrden) {
        log.info("inplementando el la llamada rest con codigo: %s", codigoOrden);
        getService(context).obtenerDetalleOrden(codigoOrden).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if(response.code() == SUCCES){
                    JsonObject data = response.body().get("data").getAsJsonObject();
                    Orden orden = new Orden();
                    orden.setCodigo(data.get("name").isJsonNull() ? null : data.get("name").getAsString());
                    orden.setNombreCliente(data.get("customer_name").isJsonNull() ? null : data.get("customer_name").getAsString());
                    orden.setFechaEntrega(data.get("delivery_date").isJsonNull() ? null : Fechas.asDate(data.get("customer_name").getAsString()));
                    orden.setTotalGeneral(data.get("base_grand_total").isJsonNull() ? null : data.get("base_grand_total").getAsDouble());
                    Lista<Producto> productos = new Lista<>();

                    recorrerLista(data.get("items").getAsJsonArray().iterator(), (item)->{
                        Producto producto = new Producto();
                        producto.setItemCode(item.get("item_code").isJsonNull() ? null : item.get("item_code").getAsString());
                        producto.setCantidad(item.get("qty").isJsonNull() ? null : item.get("qty").getAsInt());
                        producto.setPrecioUnitario(item.get("rate").isJsonNull() ? null : item.get("rate").getAsDouble());
                        producto.setNombre(item.get("description").isJsonNull() ? null : item.get("description").getAsString());
                        producto.setPrecioCantidad(item.get("base_amount").isJsonNull() ? null : item.get("base_amount").getAsDouble());
                        productos.add(producto);
                    });

                    orden.setProductos(productos);
                    succes.onRespuestaSucces(orden);

                }else{
                    error.onRespuestaError(String.format("error codigo %s", response.code()));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        } );

        return this;
    }

    @Override
    public OrdenesRepository setOnRespuestaSucces(RespuestaSucces listen) {
        this.succes = listen;
        return this;
    }

    @Override
    public OrdenesRepository setOnRespuestaError(RespuestaError listen) {
        this.error = listen;
        return this;
    }


}
