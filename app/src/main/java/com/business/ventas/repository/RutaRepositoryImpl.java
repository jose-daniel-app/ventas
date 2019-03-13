package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Ruta;
import com.business.ventas.utils.Lista;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RutaRepositoryImpl extends PadreRepository implements RutaRepository {

    RespuestaSucces succes;
    RespuestaError error;

    @Override
    public RutaRepository listarRutas(Context context) {
        getService(context).listarRutas("\"warehouse_name,name,company\"").enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == SUCCES) {
                    Lista<Ruta> rutas = new Lista<>();
                    Iterator<JsonElement> iterar = response.body().getAsJsonArray("data").iterator();
                    while (iterar.hasNext()) {
                        JsonObject obj = iterar.next().getAsJsonObject();
                        Ruta ruta = new Ruta();
                        ruta.setDirRuta(obj.get("warehouse_name").isJsonNull() ? null : obj.get("warehouse_name").getAsString());
                        ruta.setCompany(obj.get("company").isJsonNull() ? null : obj.get("company").getAsString());
                        ruta.setCodRuta(obj.get("name").isJsonNull() ? null : obj.get("name").getAsString());
                        rutas.add(ruta);
                    }
                    succes.onRespuestaSucces(rutas);

                } else {
                    error.onRespuestaError("error codigo: " +  response.code());
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
    public RutaRepository setOnRespuestaSucces(RespuestaSucces<List<Ruta>> listen) {
        this.succes = listen;
        return this;
    }

    @Override
    public RutaRepository setOnRespuestaError(RespuestaError listen) {
        this.error = listen;
        return this;
    }
}
