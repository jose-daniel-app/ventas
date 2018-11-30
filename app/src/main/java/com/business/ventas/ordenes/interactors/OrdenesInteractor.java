package com.business.ventas.ordenes.interactors;

import com.business.ventas.ordenes.contracts.OrdenesContracts;
import com.business.ventas.repository.ProductosRepository;
import com.business.ventas.repository.RepositoryFactory;

public class OrdenesInteractor implements OrdenesContracts.Interactor {

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    ProductosRepository _producto = factory.getProductosRepository();
    OrdenesContracts.Presenter presenter;

    @Override
    public OrdenesContracts.Interactor setPresenter(OrdenesContracts.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }

    @Override
    public void solicitarProductos() {
        _producto.listarProductos(presenter.getContext());
    }
}
