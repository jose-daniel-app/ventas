package com.business.ventas.apiRest;



import com.business.ventas.beans.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Service {

    @Headers("Content-type: application/json")
    @POST(Constants.URL_LOGIN)
    Call<JsonObject> login();

}
