package com.business.ventas.apiRest;

import android.content.SharedPreferences;
import android.util.Log;

import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

import java.util.HashSet;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiAdapter {

    VentasLog log = LogFactory.createInstance().setTag(RestApiAdapter.class.getSimpleName());

    public Service getLoginService() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor((chain) -> {
                    Response originalResponse = chain.proceed(chain.request());
                    //log.info("los heads : " + originalResponse.headers("Set-Cookie"));
                    if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                        HashSet<String> cookies = new HashSet<>();
                        new Lista<String>(originalResponse.headers("Set-Cookie")).foreach(item ->{
                            cookies.add(item);
                        });
                    }
                    return originalResponse;
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_ROOT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);

    }

}
