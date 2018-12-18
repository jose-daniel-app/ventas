package com.business.ventas.ordenes.interactors;

import com.business.ventas.beans.Orden;
import com.business.ventas.ordenes.contracts.OrdenesContract;
import com.business.ventas.repository.OrdenesRepository;
import com.business.ventas.repository.RepositoryFactory;
import com.business.ventas.utils.IpadreRepository;
import com.business.ventas.utils.Lista;

public class OrdenesInteractor implements OrdenesContract.Interactor {

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
                .setOnRespuestaSucces(new IpadreRepository.RespuestaSucces<Lista<Orden>>() {

                    @Override
                    public void onRespuestaSucces(Lista<Orden> ordens) {
                        presenter.getView().mostrarOrdenes(ordens);
                    }
                })
                .setOnRespuestaError(presenter.getView()::errorRespuesta);
    }
}
