package com.business.ventas.ordenes.contracts;

import com.business.ventas.beans.Cliente;
import com.business.ventas.ordenes.interactors.ClienteInteractor;
import com.business.ventas.ordenes.presenters.ClientePresenter;
import com.business.ventas.utils.PadreInteractor;
import com.business.ventas.utils.PadrePresenter;
import com.business.ventas.utils.PadreView;

import java.util.List;

public interface ClienteContract {

    String PRESENTERS = "Presenter";
    String INTERACTOR = "Interactor";

    static <T> T createInstance(Class<T> clas) {
        if (clas.getSimpleName().equalsIgnoreCase(PRESENTERS))
            return (T) new ClientePresenter();
        else if (clas.getSimpleName().equalsIgnoreCase(INTERACTOR))
            return (T)new ClienteInteractor();
        return null;
    }

    interface View extends PadreView {
        void mostrarListaClientes(List<Cliente> clientes);
    }

    interface Presenter extends PadrePresenter<Presenter, View> {
        void solicitarlistaClientes();
    }

    interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void solicitarlistaClientes();
    }
}
