package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;
import com.business.ventas.beans.User;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl extends PadreRepository implements UserRepository {

    VentasLog log = LogFactory.createInstance().setTag(UserRepositoryImpl.class.getSimpleName());

    @Override
    public void loginSesion(final String correo, final String password, final Respond<User> listener, Context context) {

        RestApiAdapter restApiAdapter = new RestApiAdapter(context);
        Service service = restApiAdapter.getLoginService();

        Call<JsonObject> call = service.login(new Service.User(correo, password));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject resp = response.body();
                User user = new User();
                if (resp != null) {
                    user.setApiKey(response.headers().get("Set-Cookie").toString().split(";")[0].split("=")[1]);
                    listener.succes(user);
                } else {
                    ResponseBody s = response.errorBody();
                    listener.error(new ErrorHandel(s).getMensaje());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                log.error(t.getMessage(), t);
                listener.error(t.getMessage());
            }
        });

    }

    @Override
    public boolean conseguirUsuarioRegistrado(Context context) {
        try {
            Response<JsonObject> response = getService(context).conseguirUsuarioRegistrado().execute();
            return response.code() == SUCCES;
        } catch (Exception e) {
            return false;
        }
    }

    private class ErrorHandel {
        private ResponseBody responseBody;
        private static final String ERROR_USER = "User disabled or missing";
        private static final String ERROR_PASS = "Incorrect password";

        public ErrorHandel(ResponseBody responseBody) {
            this.responseBody = responseBody;
        }

        public String getMensaje() {
            String mensaje = "";
            try {
                JSONObject jsonObject = new JSONObject(this.responseBody.string());
                mensaje = jsonObject.getString("message");
                log.info(jsonObject.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return verificarMensaje(mensaje);
        }

        private String verificarMensaje(String mensaje) {
            if (ERROR_USER.equals(mensaje)) {
                return "Correo no existe";
            } else if (ERROR_PASS.equals(mensaje)) {
                return "Password no existe";
            } else {
                return "error no identificado";
            }
        }
    }

}
