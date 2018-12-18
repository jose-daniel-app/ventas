package com.business.ventas.requerimiento.interactors;

import com.business.ventas.repository.ProductosRepository;
import com.business.ventas.repository.RepositoryFactory;
import com.business.ventas.requerimiento.contracts.ReqProductoContract;

public class ReqProductoInteractor implements ReqProductoContract.Interactor {

    ReqProductoContract.Presenter presenter;

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    ProductosRepository _producto = factory.getProductosRepository();

    @Override
    public void solicitarProductos() {
        _producto.listarProductos(presenter.getContext())
                .setOnRespuestaSucces(presenter.getView()::cargarProductos)
                .setOnRespuestaError(presenter.getView()::errorRespuesta);
    }

    @Override
    public ReqProductoContract.Interactor setPresenter(ReqProductoContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }
}
