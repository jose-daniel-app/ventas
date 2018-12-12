package com.business.ventas.ordenes.interactors;

import com.business.ventas.ordenes.contracts.OrdenesContract;
import com.business.ventas.repository.OrdenesRepository;
import com.business.ventas.repository.RepositoryFactory;

public class OrdenInteractor implements OrdenesContract.Interactor {

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    OrdenesRepository _orden = factory.getOrdenesRepository();

    OrdenesContract.Presenter presenter;

    @Override
    public OrdenesContract.Interactor setPresenter(OrdenesContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }

    @Override
    public void listarOrdenes() {
        _orden.listarOrdenes(presenter.getContext())
                .setOnRespuestaSucces(presenter.getView()::mostrarOrdenes)
                .setOnRespuestaError(presenter.getView()::errorRespuesta);
    }
}
