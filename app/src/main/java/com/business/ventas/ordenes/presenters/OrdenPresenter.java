package com.business.ventas.ordenes.presenters;

import android.content.Context;

import com.business.ventas.beans.Orden;
import com.business.ventas.ordenes.contracts.OrdenContract;

public class OrdenPresenter implements OrdenContract.Presenter {

    private OrdenContract.View view;
    private OrdenContract.Interactor interactor;
    private Context context;

    public OrdenPresenter() {
        interactor = OrdenContract.createInstance(OrdenContract.Interactor.class).setPresenter(this);
    }

    @Override
    public void solicitarDetalleOrden(String codigo) {
        interactor.solicitarDetalleOrden(codigo);
    }

    @Override
    public OrdenContract.Presenter setView(OrdenContract.View view) {
        this.view = view;
        return this;
    }

    @Override
    public OrdenContract.Presenter setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public OrdenContract.View getView() {
        return this.view;
    }

    @Override
    public void solicitarEliminarOrden(Orden orden) {
        interactor.solicitarEliminarOrden(orden);
    }
}
