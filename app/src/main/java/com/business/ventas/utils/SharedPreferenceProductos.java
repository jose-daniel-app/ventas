package com.business.ventas.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SharedPreferenceProductos {

    private static final String NAME_PREFERENCE = "LISTA_PRODUCTO_PREFERENCE";
    private static final String ITEM = "PRODUCTOS";

    private static final SharedPreferenceProductos ourInstance = new SharedPreferenceProductos();
    Type listType = new TypeToken<List<Item>>() {}.getType();
    private Gson gson = new Gson();
    private Activity activity;
    private List<Item> lista;

    public static SharedPreferenceProductos getInstance() {
        return ourInstance;
    }

    private SharedPreferenceProductos() {
    }

    public SharedPreferenceProductos setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }


    public void guardar(List<Item> listaItem) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        lista = listaItem;
        editor.clear();
        editor.putString(ITEM, gson.toJson(lista, listType));
        editor.apply();
    }

    public List<Item> listarItem() {
        List<Item> lista = null;
        String json = getSharedPreferences().getString(ITEM, null);
        if (json != null) {
            lista = gson.fromJson(json, listType);
        }
        return lista;
    }

    private SharedPreferences getSharedPreferences() {
        return activity.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE);
    }

    public static class Item {
        private String nombre;
        private String descripcion;
        private int cantidad;
        private double precioUnitario;
        private double precioCantidad;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(double precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public double getPrecioCantidad() {
            return precioCantidad;
        }

        public void setPrecioCantidad(double precioCantidad) {
            this.precioCantidad = precioCantidad;
        }
    }

}
