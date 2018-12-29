package com.business.ventas.repository;

import android.content.Context;

import com.google.gson.JsonArray;

public class AlmacenRepositoryImpl extends PadreRepository implements AlmacenRepository {

    @Override
    public void obtenerAlmacen(Context context, String territorio, RespuestaSucces<String> succes, RespuestaError error) {
        getService(context).obtenerAlmacen("\"*\"", "[[\"Warehouse\",\"name\",\"like\",\"%"+territorio+"%\"]]").enqueue(
            new PadreRepository.CallRespuesta().listenRespuesta( respOk -> {
                JsonArray data = respOk.body().get("data").getAsJsonArray();
                String nombreAlmacen = data.get(0).getAsJsonObject().get("name").getAsString();
                succes.onRespuestaSucces(nombreAlmacen);
            }).listenError(error::onRespuestaError)
        );
    }
}
