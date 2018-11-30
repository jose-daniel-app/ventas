package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductosRepositoryImpl implements ProductosRepository {
    VentasLog log = LogFactory.createInstance().setTag(ProductosRepositoryImpl.class.getSimpleName());
    private RespuestaSucces listenSucces;
    private RespuestaError listenError;

    @Override
    public ProductosRepository listarProductos(Context context) {

        RestApiAdapter restApiAdapter = new RestApiAdapter(context);
        Service service = restApiAdapter.getLoginService();
        Call<JsonObject> call = service
                .listarProductos("\"brand,item_name,description,item_code,standard_rate,thumbnail\"");

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    Lista<Producto> lista = new Lista<>();
                    JsonObject objeto = response.body();
                    JsonArray array = objeto.getAsJsonArray("data");
                    Iterator<JsonElement> iterar = array.iterator();
                    while (iterar.hasNext()) {
                        JsonObject object = iterar.next().getAsJsonObject();
                        log.info("elemen: " + object.toString());
                    }

                } else {
                    listenError.onRespuestaError("ocurrio un error codigo: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                listenError.onRespuestaError(t.getMessage());
            }
        });

        return this;
    }

    @Override
    public ProductosRepository setOnRespuestaSucces(RespuestaSucces<List<Producto>> listenSucces) {
        this.listenSucces = listenSucces;
        return this;
    }

    @Override
    public ProductosRepository setOnRespuestaError(RespuestaError listenError) {
        this.listenError = listenError;
        return this;
    }
}
