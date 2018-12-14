package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.apiRest.RestApiAdapter;
import com.business.ventas.apiRest.Service;

public abstract class PadreRepository  {

    protected static int SUCCES = 200;


    public Service getService(Context context) {
        RestApiAdapter restApiAdapter = new RestApiAdapter(context);
        return restApiAdapter.getLoginService();
    }
}
