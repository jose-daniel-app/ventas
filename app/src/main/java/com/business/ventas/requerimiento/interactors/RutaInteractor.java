package com.business.ventas.requerimiento.interactors;

import com.business.ventas.beans.Ruta;
import com.business.ventas.repository.RepositoryFactory;
import com.business.ventas.repository.RutaRepository;
import com.business.ventas.requerimiento.contracts.RutaContract;

public class RutaInteractor implements RutaContract.Interactor {

    private RutaContract.Presenter presenter;

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    RutaRepository _ruta = factory.getRutaRepository();

    @Override
    public void listarRutas() {
        _ruta.listarRutas(presenter.getContext())
                .setOnRespuestaSucces(presenter.getView()::mostrarRutas)
                .setOnRespuestaError(presenter.getView()::errorRespuesta);
    }

    @Override
    public RutaContract.Interactor setPresenter(RutaContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }
}
