package com.business.ventas.ordenes.presenters;

import android.content.Context;

import com.business.ventas.ordenes.contracts.OrdenesContract;

public class OrdenesPresenter implements OrdenesContract.Presenter {

    private OrdenesContract.Interactor interactor;
    private OrdenesContract.View view;
    private Context context;

    public OrdenesPresenter() {
        this.interactor = OrdenesContract
            .createInstance(OrdenesContract.Interactor.class)
            .setPresenter(this);
    }

    @Override
    public void listarOrdenes() {
        interactor.listarOrdenes();
    }

    @Override
    public OrdenesContract.Presenter setView(OrdenesContract.View view) {
        this.view = view;
        return this;
    }

    @Override
    public OrdenesContract.Presenter setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public OrdenesContract.View getView() {
        return this.view;
    }
}
