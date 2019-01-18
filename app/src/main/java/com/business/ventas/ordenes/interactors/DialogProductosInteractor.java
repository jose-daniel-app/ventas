package com.business.ventas.ordenes.interactors;

import com.business.ventas.ordenes.contracts.DialogProductosContract;
import com.business.ventas.repository.ProductosRepository;
import com.business.ventas.repository.RepositoryFactory;
import com.business.ventas.utils.Lista;

public class DialogProductosInteractor implements DialogProductosContract.Interactor {

    private DialogProductosContract.Presenter presenter;

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    ProductosRepository _productos = factory.getProductosRepository();

    @Override
    public void solicitarProductos() {
        this._productos.listarProductos(presenter.getContext())
                .setOnRespuestaSucces(productos -> {
                            presenter.getView().cargarProductos(new Lista<>(productos));
                        }
                ).setOnRespuestaError(presenter.getView()::errorRespuesta);
    }

    @Override
    public DialogProductosContract.Interactor setPresenter(DialogProductosContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }
}
