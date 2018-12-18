package com.business.ventas.comprobante.interactors;

import com.business.ventas.comprobante.contracts.DetalleGuiaContract;
import com.business.ventas.repository.ComprobanteRepository;
import com.business.ventas.repository.RepositoryFactory;

public class DetalleGuiaInteractor implements DetalleGuiaContract.Interactor {

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    ComprobanteRepository _comprobante = factory.getComprobanteRepository();
    private DetalleGuiaContract.Presenter presenter;

    @Override
    public void pedirDetalleComprobante(String codigo, int tipoComprobante) {
        _comprobante.detalleDeComprobante(presenter.getContext(), codigo, tipoComprobante,
                presenter.getView()::mostrarDetalleComprobante,
                presenter.getView()::errorRespuesta);
    }

    @Override
    public DetalleGuiaContract.Interactor setPresenter(DetalleGuiaContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }
}
