package com.business.ventas.comprobante.interactors;

import com.business.ventas.comprobante.contracts.ComprobanteContract;
import com.business.ventas.repository.ComprobanteRepository;
import com.business.ventas.repository.RepositoryFactory;

public class ComprobanteInteractor implements ComprobanteContract.Interactor {

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    ComprobanteRepository _comprobante = factory.getComprobanteRepository();
    ComprobanteContract.Presenter presenter;

    @Override
    public void pedirComprobantes() {
        _comprobante.listarComprovantes(
                presenter.getContext(),
                presenter.getView()::mostrarComprovantes,
                presenter.getView()::errorRespuesta
        );
    }

    @Override
    public ComprobanteContract.Interactor setPresenter(ComprobanteContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }
}
