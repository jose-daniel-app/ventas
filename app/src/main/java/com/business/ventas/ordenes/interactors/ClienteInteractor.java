package com.business.ventas.ordenes.interactors;

import com.business.ventas.ordenes.contracts.ClienteContract;
import com.business.ventas.repository.ClientesRepository;
import com.business.ventas.repository.RepositoryFactory;

public class ClienteInteractor implements ClienteContract.Interactor {

    ClienteContract.Presenter presenter;
    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    ClientesRepository _cliente = factory.getClientesRepository();

    @Override
    public void solicitarlistaClientes() {
        _cliente.listarClientes(presenter.getContext())
                .setOnRespuestaSucces(presenter.getView()::mostrarListaClientes)
                .setOnRespuestaError(presenter.getView()::errorRespuesta);
    }

    @Override
    public ClienteContract.Interactor setPresenter(ClienteContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }
}
