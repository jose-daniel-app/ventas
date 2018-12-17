package com.business.ventas.apiRest;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(Constants.URL_LOGIN)
    Call<JsonObject> login(@Body User User);

    @GET(Constants.URL_LISTA_PRODUCTO)
    Call<JsonObject> listarProductos(@Query("fields") String fields);

    @GET(Constants.URL_LISTA_CLIENTES)
    Call<JsonObject> listarClientes(@Query("fields") String fields, @Query("filters") String filters);

    @GET(Constants.URL_LISTA_ORDENES)
    Call<JsonObject> listarOrdenes(@Query("fields") String fields);

    @GET(Constants.URL_DETALLE_ORDEN)
    Call<JsonObject> obtenerDetalleOrden(@Path("codigo") String codigo);

    @GET(Constants.URL_LISTA_RUTAS)
    Call<JsonObject> listarRutas(@Query("fields") String fields);

    @GET(Constants.URL_LISTA_FACTURAS)
    Call<JsonObject> listarFacturas(@Query("fields") String fields);

    @GET(Constants.URL_LISTA_GUIAS)
    Call<JsonObject> listarGuias(@Query("fields") String fields);

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

