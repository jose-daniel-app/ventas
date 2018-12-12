package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;
import com.business.ventas.beans.Orden;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;

public class OrdenesRepositoryImpl implements OrdenesRepository {

    RespuestaSucces succes;
    RespuestaError error;

    @Override
    public OrdenesRepository listarOrdenes(Context context) {

        RestApiAdapter restApiAdapter = new RestApiAdapter(context);
        Service service = restApiAdapter.getLoginService();
        Call<JsonObject> call = service.listarProductos("\"*\"");

        return this;
    }

    @Override
    public OrdenesRepository setOnRespuestaSucces(RespuestaSucces<List<Orden>> listen) {
        this.succes = listen;
        return this;
    }

    @Override
    public OrdenesRepository setOnRespuestaError(RespuestaError listen) {
        this.error = listen;
        return this;
    }
}
