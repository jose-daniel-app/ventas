package com.business.ventas.apiRest;




import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Service {

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(Constants.URL_LOGIN)
    Call<JsonObject> login(@Body User User);

    public class User {
        public User(String usr, String pwd) {
            this.usr = usr;
            this.pwd = pwd;
        }

        private String usr;
        private String pwd;

        public String getUsr() {
            return usr;
        }

        public void setUsr(String usr) {
            this.usr = usr;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }

}

