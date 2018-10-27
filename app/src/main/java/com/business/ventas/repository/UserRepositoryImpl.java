package com.business.ventas.repository;

import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;
import com.business.ventas.beans.User;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {

    VentasLog log = LogFactory.createInstance().setTag(UserRepositoryImpl.class.getSimpleName());

    @Override
    public void loginSesion(final String correo, final String password, final Respond<User> listener) {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getLoginService();
        Call<Void> call =  service.login(new Service.User(correo,password));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                log.info(response.headers().get("Authorization"));
                User user = new User();
                user.setApiKey(response.headers().get("Authorization"));
                listener.succes(user);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                log.error(t.getMessage(),t);
                listener.error(t.getMessage());
            }
        });

    }



}
