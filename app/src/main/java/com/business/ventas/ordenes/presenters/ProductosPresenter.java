package com.business.ventas.ordenes.presenters;

import android.content.Context;

import com.business.ventas.ordenes.contracts.ProductosContracts;

public class ProductosPresenter implements ProductosContracts.Presenter {

    private Context context;
    private ProductosContracts.View view;
    private ProductosContracts.Interactor interactor;

    public ProductosPresenter() {
        interactor = ProductosContracts.createInstance(ProductosContracts.Interactor.class)
                .setPresenter(this);
    }

    @Override
    public ProductosContracts.Presenter setView(ProductosContracts.View view) {
        this.view = view;
        return this;
    }

    @Override
    public ProductosContracts.Presenter SetContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public ProductosContracts.View getView() {
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
