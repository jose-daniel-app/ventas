package com.business.ventas.ordenes.presenters;

import android.content.Context;

import com.business.ventas.ordenes.contracts.ProductosContract;

public class ProductosPresenter implements ProductosContract.Presenter {

    private Context context;
    private ProductosContract.View view;
    private ProductosContract.Interactor interactor;

    public ProductosPresenter() {
        interactor = ProductosContract.createInstance(ProductosContract.Interactor.class)
                .setPresenter(this);
    }

    @Override
    public ProductosContract.Presenter setView(ProductosContract.View view) {
        this.view = view;
        return this;
    }

    @Override
    public ProductosContract.Presenter setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public ProductosContract.View getView() {
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
