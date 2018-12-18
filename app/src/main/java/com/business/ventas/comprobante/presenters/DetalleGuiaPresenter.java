package com.business.ventas.comprobante.presenters;

import android.content.Context;

import com.business.ventas.comprobante.contracts.DetalleGuiaContract;

public class DetalleGuiaPresenter implements DetalleGuiaContract.Presenter {

    private DetalleGuiaContract.View view;
    private DetalleGuiaContract.Interactor interactor;
    private Context context;

    public DetalleGuiaPresenter() {
        this.interactor = DetalleGuiaContract.createInstance(DetalleGuiaContract.Interactor.class).setPresenter(this);
    }

    @Override
    public void pedirDetalleComprobante(String codigo, int tipoComprobante) {
        interactor.pedirDetalleComprobante(codigo,tipoComprobante);
    }

    @Override
    public DetalleGuiaContract.Presenter setView(DetalleGuiaContract.View view) {
        this.view = view;
        return this;
    }

    @Override
    public DetalleGuiaContract.Presenter setContext(Context context) {
        this.context =context;
        return this;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public DetalleGuiaContract.View getView() {
        return this.view;
    }
}
