package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.R;
import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;
import com.business.ventas.beans.Cliente;
import com.business.ventas.utils.Lista;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientesRepositoryImpl implements ClientesRepository {

    RespuestaSucces succes;
    RespuestaError error;

    @Override
    public ClientesRepository listarClientes(Context context) {

        RestApiAdapter restApiAdapter = new RestApiAdapter(context);
        Service service = restApiAdapter.getLoginService();
        Call<JsonObject> call = service
                .listarClientes("\"*\"", "[[\"Customer\",\"territory\",\"=\",\"Arequipa1\"]]");

        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    Lista<Cliente> lista = new Lista<>();
                    JsonArray array = response.body().getAsJsonArray("data");
                    Iterator<JsonElement> iterar = array.iterator();
                    while (iterar.hasNext()) {
                        JsonObject object = iterar.next().getAsJsonObject();
                        Cliente cliente = new Cliente();
                        cliente.setNombre(object.get("customer_name").getAsString());
                        cliente.setDireccion(object.get("territory").getAsString());
                        cliente.setRuc("46829697123");
                        cliente.setFoto(R.drawable.ic_account_circle_black_24dp);
                        lista.add(cliente);
                    }
                    succes.onRespuestaSucces(lista);
                } else {
                    error.onRespuestaError(String.format("error codigo %s", response.code()));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                error.onRespuestaError(t.getMessage());
            }
        });

        return this;
    }

    @Override
    public ClientesRepository setOnRespuestaSucces(RespuestaSucces<List<Cliente>> listen) {
        this.succes = listen;
        return this;
    }

    @Override
    public ClientesRepository setOnRespuestaError(RespuestaError listen) {
        this.error = listen;
        return this;
    }
}
