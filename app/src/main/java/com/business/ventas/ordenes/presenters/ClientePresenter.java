package com.business.ventas.ordenes.presenters;

import android.content.Context;

import com.business.ventas.ordenes.contracts.ClienteContract;

public class ClientePresenter implements ClienteContract.Presenter {

    ClienteContract.Interactor interactor;
    ClienteContract.View view;
    Context context;


    public ClientePresenter() {
        interactor = ClienteContract.createInstance(ClienteContract.Interactor.class).setPresenter(this);
    }

    @Override
    public void solicitarlistaClientes() {
        interactor.solicitarlistaClientes();
    }

    @Override
    public ClienteContract.Presenter setView(ClienteContract.View view) {
        this.view = view;
        return this;
    }

    @Override
    public ClienteContract.Presenter setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public ClienteContract.View getView() {
        return view;
    }
}
