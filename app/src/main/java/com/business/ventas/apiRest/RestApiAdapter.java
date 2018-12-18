package com.business.ventas.apiRest;

import android.content.Context;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.SharedPrefedenceCookies;
import com.business.ventas.utils.VentasLog;

import java.util.HashSet;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiAdapter {

    Context context;
    SharedPrefedenceCookies shareCookies;
    VentasLog log = LogFactory.createInstance().setTag(RestApiAdapter.class.getSimpleName());

    public RestApiAdapter(Context context) {
        this.context = context;
        shareCookies = new SharedPrefedenceCookies(context);
    }

    public Service getLoginService() {

        OkHttpClient client = new OkHttpClient.Builder()
                // se obtiene las cookies
            .addInterceptor((chain) -> {
                Response originalResponse = chain.proceed(chain.request());
                if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                    HashSet<String> cookies = new HashSet<String>();
                    new Lista<String>(originalResponse.headers("Set-Cookie")).foreach(cookies::add);
                    shareCookies.guardarCokkies(cookies);
                }
                return originalResponse;
            })
                // se envian las cookies
            .addInterceptor((chain) -> {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("Cookie", shareCookies
                        .concatenateCookies(cookie -> cookie.split(";")[0] + ";"));
                return chain.proceed(builder.build());
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
