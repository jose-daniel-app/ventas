package com.business.ventas.comprobante.presenters;

import android.content.Context;

import com.business.ventas.comprobante.contracts.DocumentoPendienteContract;

public class DocumentoPendientePresenter implements DocumentoPendienteContract.Presenter {

    private DocumentoPendienteContract.Interactor interactor;
    private DocumentoPendienteContract.View view;
    private Context context;

    public DocumentoPendientePresenter() {
        this.interactor = DocumentoPendienteContract.createInstance(DocumentoPendienteContract.Interactor.class).setPresenter(this);
    }

    @Override
    public void pedirDetalleComprobante(String codigo, int tipoComprobante) {
        this.interactor.pedirDetalleComprobante(codigo, tipoComprobante);
    }

    @Override
    public DocumentoPendienteContract.Presenter setView(DocumentoPendienteContract.View view) {
        this.view = view;
        return this;
    }

    @Override
    public DocumentoPendienteContract.Presenter setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public DocumentoPendienteContract.View getView() {
        return this.view;
    }
}
