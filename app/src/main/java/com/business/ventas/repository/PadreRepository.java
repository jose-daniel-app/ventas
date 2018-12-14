package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;

public abstract class PadreRepository {

    protected static int SUCCES = 200;


    public Service getService(Context context) {
        RestApiAdapter restApiAdapter = new RestApiAdapter(context);
        return restApiAdapter.getLoginService();
    }

    protected void recorrerLista(Iterator<JsonElement> iterator, RecorrersonObject recorrer) {
        while (iterator.hasNext()) {
            recorrer.onItemJsonObject(iterator.next().getAsJsonObject());
        }
    }

    @FunctionalInterface
    interface RecorrersonObject {
        void onItemJsonObject(JsonObject json);
    }
}
