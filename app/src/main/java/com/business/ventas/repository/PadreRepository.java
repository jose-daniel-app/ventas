package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class PadreRepository {

    protected static int SUCCES = 200;


    public Service getService(Context context) {
        RestApiAdapter restApiAdapter = new RestApiAdapter(context);
        return restApiAdapter.getLoginService();
    }

    protected String getString(JsonElement element){
        return element.isJsonNull() ? null : element.getAsString();
    }

    protected double getDouble(JsonElement element){
        return element.isJsonNull() ? null : element.getAsDouble();
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

    public static class CallRespuesta implements Callback<JsonObject> {

        private ICallRespuesta iCallRespuesta;

        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if(this.iCallRespuesta!= null){
                iCallRespuesta.onICallRespuesta(response);
            }
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {

        }

        public CallRespuesta listenRespuesta(ICallRespuesta iCallRespuesta){
            this.iCallRespuesta = iCallRespuesta;
            return this;
        }

        interface ICallRespuesta {
            void onICallRespuesta(Response<JsonObject> response);
        }


    }
}
