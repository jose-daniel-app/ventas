package com.business.ventas.apiRest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRestAdapter {

    public Service getClientService(){

        /*OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + Constants.BEARER)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build(); */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_ROOT)
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }
}
