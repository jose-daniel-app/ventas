package com.business.ventas.requerimiento.presenters;

import android.content.Context;

import com.business.ventas.beans.Requerimiento;
import com.business.ventas.requerimiento.contracts.ReqProductoContract;

public class ReqProductoPresenter implements ReqProductoContract.Presenter {

    private ReqProductoContract.View view;
    private ReqProductoContract.Interactor interactor;
    private Context context;

    public ReqProductoPresenter() {
        this.interactor = ReqProductoContract
                .createInstance(ReqProductoContract.Interactor.class)
                .setPresenter(this);
    }

    @Override
    public void solicitarProductos() {
        this.interactor.solicitarProductos();
    }

    @Override
    public ReqProductoContract.Presenter setView(ReqProductoContract.View view) {
        this.view = view;
        return this;
    }

    @Override
    public ReqProductoContract.Presenter setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public ReqProductoContract.View getView() {
        return this.view;
    }

    @Override
    public void crearRequerimiento(Requerimiento requerimiento) {
        this.interactor.crearRequerimiento(requerimiento);
    }
}
