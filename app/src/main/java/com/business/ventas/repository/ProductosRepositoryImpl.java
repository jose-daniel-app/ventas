package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.apiRest.Constants;
import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.Numeros;
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
    private RespuestaSucces<List<Producto>> listenSucces;
    private RespuestaError listenError;

    @Override
    public ProductosRepository listarProductos(Context context) {

        RestApiAdapter restApiAdapter = new RestApiAdapter(context);
        Service service = restApiAdapter.getLoginService();
        Call<JsonObject> call = service
                .listarProductos("\"brand,item_name,item_code,standard_rate,thumbnail,is_stock_item\"");

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    Lista<Producto> lista = new Lista<>();
                    JsonArray array = response.body().getAsJsonArray("data");
                    Iterator<JsonElement> iterar = array.iterator();
                    while (iterar.hasNext()) {
                        JsonObject object = iterar.next().getAsJsonObject();
                        if (contieneNull(object)) continue;
                        lista.add(new Producto().config()
                                .setItemCode(object.get("item_code").getAsString())
                                .setStock(object.get("is_stock_item").getAsInt())
                                .setPathImg(Constants.URL_ROOT + object.get("thumbnail").getAsString())
                                .setNombre(object.get("brand").getAsString() + " " + object.get("item_name").getAsString())
                                .setPrecioUnitario(Numeros.getDouble(object.get("standard_rate").getAsString()))
                                .build()
                        );
                    }
                    listenSucces.onRespuestaSucces(lista);
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

    private boolean contieneNull(JsonObject obj) {

        return (obj.get("item_code").isJsonNull() ||
                obj.get("thumbnail").isJsonNull() ||
                obj.get("brand").isJsonNull() ||
                obj.get("item_name").isJsonNull() ||
                obj.get("is_stock_item").isJsonNull() ||
                obj.get("standard_rate").isJsonNull()
        );
    }
}
