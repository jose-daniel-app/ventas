package com.business.ventas.apiRest;



import com.business.ventas.beans.User;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Service {
    //@Headers("content-type:application/json")
    @POST(Constants.URL_LOGIN)
    Call<JsonObject> login(@Body User user);

    public class User{
        public User(String username, String password){
            this.usr = username;
            this.pwd = password;
        }
        String usr;
        String pwd;
    }

}

