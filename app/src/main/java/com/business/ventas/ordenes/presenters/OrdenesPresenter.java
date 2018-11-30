package com.business.ventas.ordenes.presenters;

import android.content.Context;

import com.business.ventas.ordenes.contracts.OrdenesContracts;

public class OrdenesPresenter implements OrdenesContracts.Presenter {

    private Context context;
    private OrdenesContracts.View view;
    private OrdenesContracts.Interactor interactor;

    public OrdenesPresenter() {
        interactor = OrdenesContracts.createInstance(OrdenesContracts.Interactor.class)
                .setPresenter(this);
    }

    @Override
    public OrdenesContracts.Presenter setView(OrdenesContracts.View view) {
        this.view = view;
        return this;
    }

    @Override
    public OrdenesContracts.Presenter SetContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public OrdenesContracts.View getView() {
        return view;
    }

    @Override
    public void solicitarProductos() {
        interactor.solicitarProductos();
    }

    @Override
    public Context getContext() {
        return this.context;
    }
}
