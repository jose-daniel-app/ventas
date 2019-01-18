package com.business.ventas.ordenes.presenters;

import android.content.Context;

import com.business.ventas.ordenes.contracts.DialogProductosContract;

public class DialogProductosPresenter implements DialogProductosContract.Presenter {

    private DialogProductosContract.View view;
    private DialogProductosContract.Interactor interactor;
    private Context context;

    public DialogProductosPresenter() {
        this.interactor = DialogProductosContract
                .createInstance(DialogProductosContract.Interactor.class)
                .setPresenter(this);
    }

    @Override
    public void solicitarProductos() {
       this.interactor.solicitarProductos();
    }

    @Override
    public DialogProductosContract.Presenter setView(DialogProductosContract.View view) {
        this.view = view;
        return this;
    }

    @Override
    public DialogProductosContract.Presenter setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public DialogProductosContract.View getView() {
        return this.view;
    }
}
