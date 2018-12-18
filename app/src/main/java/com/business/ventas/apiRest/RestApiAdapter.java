package com.business.ventas.apiRest;

import android.util.Log;

import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiAdapter {

    VentasLog log = LogFactory.createInstance().setTag(RestApiAdapter.class.getSimpleName());

    public Service getLoginService() {

        OkHttpClient client = new OkHttpClient.Builder()
                /*.addInterceptor((chain) -> {
                    Request newRequest = chain.request().newBuilder()
                            //.addHeader("Accept", "application/json")
                            .header("Content-Type", "application/json")
                            .build();
                    return chain.proceed(newRequest);
                })*/
                .addInterceptor((chain) -> {
                    Response originalResponse = chain.proceed(chain.request());
                    log.info("los heads : " + originalResponse.headers("Set-Cookie"));
                    if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                        new Lista<String>(originalResponse.headers("Set-Cookie")).foreach(item ->{
                            log.info("Cookie: " + item);
                        });
                    }
                    return originalResponse;
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_ROOT)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);

    }

}
