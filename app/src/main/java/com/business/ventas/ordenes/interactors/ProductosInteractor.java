package com.business.ventas.ordenes.interactors;

import com.business.ventas.ordenes.contracts.ProductosContracts;
import com.business.ventas.repository.ProductosRepository;
import com.business.ventas.repository.RepositoryFactory;

public class ProductosInteractor implements ProductosContracts.Interactor {

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    ProductosRepository _producto = factory.getProductosRepository();
    ProductosContracts.Presenter presenter;

    @Override
    public ProductosContracts.Interactor setPresenter(ProductosContracts.Presenter presenter) {
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
