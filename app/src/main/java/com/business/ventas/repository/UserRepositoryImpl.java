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
    public void loginSesion(final String correo, final String password, final Respond<User> listener) {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getLoginService();
        Call<JsonObject> call =  service.login(new Service.User(correo,password));

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                log.info("es null : " + (response.raw().request().headers().get("Set-Cookie")== null));
                log.info("es null2 : " + response.headers().get("Cookie") == null ? "true": response.headers().get("Cookie"));
                User user = new User();
                user.setApiKey(response.headers().get("Cookie"));
                listener.succes(user);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                log.error(t.getMessage(),t);
                listener.error(t.getMessage());
            }
        });

    }



}
