package com.business.ventas.ordenes.contracts;

import com.business.ventas.beans.Orden;
import com.business.ventas.ordenes.interactors.OrdenInteractor;
import com.business.ventas.ordenes.presenters.OrdenPresenter;
import com.business.ventas.utils.PadreInteractor;
import com.business.ventas.utils.PadrePresenter;
import com.business.ventas.utils.PadreView;

import java.util.List;

public interface OrdenesContract {

    String PRESENTERS = "Presenter";
    String INTERACTOR = "Interactor";

    static <T> T createInstance(Class<T> clas) {
        if (clas.getSimpleName().equalsIgnoreCase(PRESENTERS))
            return (T) new OrdenPresenter();
        else if (clas.getSimpleName().equalsIgnoreCase(INTERACTOR))
            return (T)new OrdenInteractor();
        return null;
    }

    interface View extends PadreView {
        void mostrarOrdenes(List<Orden> ordenes);
    }

    interface Presenter extends PadrePresenter<Presenter, View> {
        void listarOrdenes();
    }

    interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void listarOrdenes();
    }
}
