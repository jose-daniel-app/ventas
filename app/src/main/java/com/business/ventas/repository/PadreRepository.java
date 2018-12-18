package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
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

    protected String getString(JsonElement element) {
        return element.isJsonNull() ? null : element.getAsString();
    }

    protected double getDouble(JsonElement element) {
        return element.isJsonNull() ? null : element.getAsDouble();
    }

    protected int getInt(JsonElement element) {
        return element.isJsonNull() ? null : element.getAsInt();
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

        protected VentasLog log = LogFactory.createInstance().setTag(CallRespuesta.class.getSimpleName());

        private ICallRespuestaSucces iCallRespuestaSucces;
        private ICallRespuestaError iCallRespuestaError;

        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (this.iCallRespuestaSucces != null) {
                if (response.code() == SUCCES) {
                    iCallRespuestaSucces.onICallRespuesta(response);
                } else {
                    if (this.iCallRespuestaError != null)
                        iCallRespuestaError.onICallRespuesta("codigo respuesta :" + response.code());
                }
            }
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {
            log.info(t.getMessage());
            if (this.iCallRespuestaError != null)
                iCallRespuestaError.onICallRespuesta(t.getMessage());
        }

        public CallRespuesta listenRespuesta(ICallRespuestaSucces iCallRespuesta) {
            this.iCallRespuestaSucces = iCallRespuesta;
            return this;
        }

        public CallRespuesta listenError(ICallRespuestaError iCallRespuestaError) {
            this.iCallRespuestaError = iCallRespuestaError;
            return this;
        }

        interface ICallRespuestaSucces {
            void onICallRespuesta(Response<JsonObject> response);
        }

        interface ICallRespuestaError {
            void onICallRespuesta(String mensajeError);
        }
    }
}
