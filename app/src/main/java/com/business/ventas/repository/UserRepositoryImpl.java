package com.business.ventas.repository;

import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;
import com.business.ventas.beans.User;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {

    VentasLog log = LogFactory.createInstance().setTag(UserRepositoryImpl.class.getSimpleName());

    @Override
    public void loginSesion(String correo, String password, final Respond<User> listener) {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getLoginService(correo, password);
        Call<JsonObject> call =  service.login();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                log.info(response.headers().get("Authorization"));
                listener.succes(new User());

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                log.error(t.getMessage(),t);
                listener.error(t.getMessage());
            }
        });

    }
}
