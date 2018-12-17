package com.business.ventas.comprobante.presenters;

import android.content.Context;

import com.business.ventas.comprobante.contracts.ComprobanteContract;

public class ComprobantePresenter implements ComprobanteContract.Presenter {

    private ComprobanteContract.View view;
    private ComprobanteContract.Interactor interactor;
    private Context context;

    public ComprobantePresenter() {
        this.interactor = ComprobanteContract.createInstance(ComprobanteContract.Interactor.class).setPresenter(this);
    }

    @Override
    public void pedirComprobantes() {
        interactor.pedirComprobantes();
    }

    @Override
    public ComprobanteContract.Presenter setView(ComprobanteContract.View view) {
        this.view = view;
        return this;
    }

    @Override
    public ComprobanteContract.Presenter setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public ComprobanteContract.View getView() {
        return this.view;
    }
}
