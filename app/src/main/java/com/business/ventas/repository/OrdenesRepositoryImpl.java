package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;
import com.business.ventas.beans.Orden;
import com.business.ventas.utils.Lista;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdenesRepositoryImpl extends PadreRepository implements OrdenesRepository {

    RespuestaSucces succes;
    RespuestaError error;

    @Override
    public OrdenesRepository listarOrdenes(Context context) {

        RestApiAdapter restApiAdapter = new RestApiAdapter(context);
        Service service = restApiAdapter.getLoginService();
        Call<JsonObject> call = service.listarOrdenes("\"name,customer_name,customer,customer_address,address_display,other_charges_calculation\"");

        call.enqueue(new Callback<JsonObject>() {
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

        getService(context).obtenerDetalleOrden(codigoOrden).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if(response.code() == SUCCES){
                    JsonObject data = response.body().get("data").getAsJsonObject();
                    Orden orden = new Orden();
                    orden.setCodigo(data.get("name").isJsonNull() ? null : data.get("name").getAsString());
                    orden.setNombreCliente(data.get("customer_name").isJsonNull() ? null : data.get("customer_name").getAsString());
                    orden.setFechaEntrega(data.get("delivery_date").isJsonNull() ? null : data.get("customer_name").getAs);
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
