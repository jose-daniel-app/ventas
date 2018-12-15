package com.business.ventas.ordenes.interactors;

import com.business.ventas.beans.Orden;
import com.business.ventas.ordenes.contracts.OrdenContract;
import com.business.ventas.repository.OrdenesRepository;
import com.business.ventas.repository.RepositoryFactory;
import com.business.ventas.utils.IpadreRepository;

public class OrdenInteractor implements OrdenContract.Interactor {

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    OrdenesRepository _orden = factory.getOrdenesRepository();

    private OrdenContract.Presenter presenter;

    @Override
    public void solicitarDetalleOrden(String codigo) {
        _orden.detalleOrden(this.presenter.getContext(),codigo).setOnRespuestaSucces(new IpadreRepository.RespuestaSucces<Orden>() {
            @Override
            public void onRespuestaSucces(Orden orden) {
                presenter.getView().mostrarDetalleOrden(orden);
            }
        }).setOnRespuestaError(presenter.getView()::errorRespuesta);
    }

    @Override
    public OrdenContract.Interactor setPresenter(OrdenContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }
}
