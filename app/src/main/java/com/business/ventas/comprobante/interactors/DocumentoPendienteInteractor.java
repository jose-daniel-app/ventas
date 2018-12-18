package com.business.ventas.comprobante.interactors;

import com.business.ventas.comprobante.contracts.DocumentoPendienteContract;
import com.business.ventas.repository.ComprobanteRepository;
import com.business.ventas.repository.RepositoryFactory;

public class DocumentoPendienteInteractor implements DocumentoPendienteContract.Interactor {

    private RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    private ComprobanteRepository _comprobante = factory.getComprobanteRepository();
    private DocumentoPendienteContract.Presenter presenter;

    @Override
    public void pedirDetalleComprobante(String codigo, int tipoComprobante) {
        _comprobante.detalleDeComprobante(
            presenter.getContext(),codigo,tipoComprobante,
            presenter.getView()::mostrarDetalleComprobante,
            presenter.getView()::errorRespuesta
        );
    }

    @Override
    public DocumentoPendienteContract.Interactor setPresenter(DocumentoPendienteContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }
}
