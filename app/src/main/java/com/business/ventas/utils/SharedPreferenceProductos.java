package com.business.ventas.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.business.ventas.beans.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceProductos {

    private static final String NAME_PREFERENCE = "LISTA_PRODUCTO_PREFERENCE";
    private static final String ITEM = "PRODUCTOS";

    private static final SharedPreferenceProductos ourInstance = new SharedPreferenceProductos();
    Type listType = new TypeToken<List<Producto>>() {}.getType();
    private Gson gson = new Gson();
    private Activity activity;
    private List<Producto> lista = new ArrayList<>();

    public static SharedPreferenceProductos getInstance() {
        return ourInstance;
    }

    private SharedPreferenceProductos() {
    }

    public SharedPreferenceProductos setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public void guardar(List<Producto> lista) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        limpiar();
        this.lista = lista;
        editor.clear();
        editor.putString(ITEM, gson.toJson(this.lista, listType));
        editor.apply();
    }

    private void limpiar() {
        if (this.lista != null)
            this.lista = null;
    }

    public List<Producto> listarProducto() {
        List<Producto> lista = null;
        String json = getSharedPreferences().getString(ITEM, null);
        if (json != null)
            lista = gson.fromJson(json, listType);
        return lista;
    }

    private SharedPreferences getSharedPreferences() {
        return activity.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE);
    }

}
