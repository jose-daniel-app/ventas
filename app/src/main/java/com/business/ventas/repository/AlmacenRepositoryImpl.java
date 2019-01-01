package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.google.gson.JsonArray;

public class AlmacenRepositoryImpl extends PadreRepository implements AlmacenRepository {
    VentasLog log = LogFactory.createInstance().setTag(AlmacenRepositoryImpl.class.getSimpleName());
    @Override
    public void obtenerAlmacen(Context context, String territorio, RespuestaSucces<String> succes, RespuestaError error) {
        log.info("territorio seleccionado %s ", territorio);
        getService(context).obtenerAlmacen("\"*\"", "[[\"Warehouse\",\"name\",\"like\",\"%"+territorio+"%\"]]").enqueue(
            new PadreRepository.CallRespuesta().listenRespuesta( respOk -> {
                JsonArray data = respOk.body().get("data").getAsJsonArray();
                if(data.size() > 0){
                    String nombreAlmacen = data.get(0).getAsJsonObject().get("name").getAsString();
                    succes.onRespuestaSucces(nombreAlmacen);
                }else {
                    error.onRespuestaError("no se encontro almacen con el territorio " + territorio);
                }
            }).listenError(error::onRespuestaError)
        );
    }
}
