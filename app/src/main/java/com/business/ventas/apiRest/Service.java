package com.business.ventas.apiRest;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(Constants.URL_LOGIN)
    Call<JsonObject> login(@Body User User);

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_LISTA_PRODUCTO)
    Call<JsonObject> listarProductos(@Query("fields") String fields);

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_LOGIN_ESTADO)
    Call<JsonObject> conseguirUsuarioRegistrado();

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_CERRAR_SESSION)
    Call<JsonObject> cerrarSession();

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_LISTA_CLIENTES)
    Call<JsonObject> listarClientes(@Query("fields") String fields/*, @Query("filters") String filters*/);

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_LISTA_ORDENES)
    Call<JsonObject> listarOrdenes(@Query("fields") String fields);

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_DETALLE_ORDEN)
    Call<JsonObject> obtenerDetalleOrden(@Path("codigo") String codigo);

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_LISTA_RUTAS)
    Call<JsonObject> listarRutas(@Query("fields") String fields);

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_LISTA_FACTURAS)
    Call<JsonObject> listarFacturas(@Query("fields") String fields);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(Constants.URL_CREAR_ORDEN)
    Call<JsonObject> crearOrden(@Body JsonObject jsonObject);

    @Headers({"Accept: application/json"})
    @DELETE(Constants.URL_ELIMINAR_ORDEN)
    Call<JsonObject> eliminarOrden(@Path("codigo") String codigo);

    //@GET(Constants.URL_LISTA_FACTURAS)
    //Observable<Response<JsonObject>> listarFacturas1(@Query("fields") String fields);
    //@GET(Constants.URL_LISTA_GUIAS)
    //Observable<Response<JsonObject>> listarGuias1(@Query("fields") String fields);

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_LISTA_GUIAS)
    Call<JsonObject> listarGuias(@Query("fields") String fields);

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_DETALLE_FACTURA)
    Call<JsonObject> obtenerDetalleFactura(@Path("codigo") String codigo);

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_DETALLE_GUI)
    Call<JsonObject> obtenerDetalleGuia(@Path("codigo") String codigo);

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_OBTENER_ALMACEN)
    Call<JsonObject> obtenerAlmacen(@Query("fields") String fields, @Query("filters") String filters);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @PUT(Constants.URL_UPDATE_ORDEN)
    Call<JsonObject> actualizarOrden(@Path("codigo") String codigo,@Body JsonObject jsonObject);

    @Headers({"Accept: application/json"})
    @GET(Constants.URL_LISTA_REQUERIMIENTOS)
    Call<JsonObject> listarRequerimientos(@Query("fields") String fields);

     class User {
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

