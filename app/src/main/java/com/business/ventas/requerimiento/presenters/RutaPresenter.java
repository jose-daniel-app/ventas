package com.business.ventas.requerimiento.presenters;

import android.content.Context;

import com.business.ventas.requerimiento.contracts.RutaContract;

public class RutaPresenter implements RutaContract.Presenter {

    private  RutaContract.Interactor interactor;
    private RutaContract.View view;
    private Context context;

    public RutaPresenter() {
        this.interactor = RutaContract
                .createInstance(RutaContract.Interactor.class)
                .setPresenter(this);
    }

    @Override
    public void listarRutas() {
        this.interactor.listarRutas();
    }

    @Override
    public RutaContract.Presenter setView(RutaContract.View view) {
        this.view = view;
        return this;
    }

    @Override
    public RutaContract.Presenter setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public RutaContract.View getView() {
        return view;
    }
}
