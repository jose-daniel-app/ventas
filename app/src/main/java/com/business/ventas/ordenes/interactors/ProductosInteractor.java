package com.business.ventas.ordenes.interactors;

import com.business.ventas.ordenes.contracts.ProductosContract;
import com.business.ventas.repository.ProductosRepository;
import com.business.ventas.repository.RepositoryFactory;

public class ProductosInteractor implements ProductosContract.Interactor {

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    ProductosRepository _producto = factory.getProductosRepository();
    ProductosContract.Presenter presenter;

    @Override
    public ProductosContract.Interactor setPresenter(ProductosContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }

    @Override
    public void solicitarProductos() {
        _producto.listarProductos(presenter.getContext())
            .setOnRespuestaSucces(presenter.getView()::cargarProductos)
            .setOnRespuestaError(presenter.getView()::errorRespuesta);
    }
}