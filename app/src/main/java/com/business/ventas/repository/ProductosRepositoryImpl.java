package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.apiRest.Constants;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

import com.google.gson.JsonObject;
import java.util.List;

public class ProductosRepositoryImpl  extends PadreRepository implements ProductosRepository {
    VentasLog log = LogFactory.createInstance().setTag(ProductosRepositoryImpl.class.getSimpleName());
    private RespuestaSucces<List<Producto>> listenSucces;
    private RespuestaError listenError;

    @Override
    public ProductosRepository listarProductos(Context context) {

        getService(context)
            .listarProductos("\"brand,item_name,item_code,standard_rate,thumbnail,is_stock_item\"")
            .enqueue(new PadreRepository.CallRespuesta().listenRespuesta(respOk -> {
                Lista<Producto> lista = new Lista<>();
                recorrerLista(respOk.body().getAsJsonArray("data").iterator(), item -> {
                    lista.add(
                        new Producto().config()
                            .setItemCode(getString(item.get("item_code")))
                            .setStock(getInt(item.get("is_stock_item")))
                            .setPathImg( getString(item.get("thumbnail")) == null ? null : Constants.URL_ROOT + getString(item.get("thumbnail")))
                            .setNombre(getString(item.get("brand")) + " " + getString(item.get("item_name")))
                            .setPrecioUnitario(getDouble(item.get("standard_rate")))
                            .build()
                    );
                });
                listenSucces.onRespuestaSucces(lista);
            }).listenError(error -> {
                listenError.onRespuestaError(error);
            }));

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
