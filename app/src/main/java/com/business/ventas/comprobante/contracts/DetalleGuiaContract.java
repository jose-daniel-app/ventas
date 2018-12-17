package com.business.ventas.comprobante.contracts;

import com.business.ventas.beans.Comprobante;
import com.business.ventas.comprobante.interactors.DetalleGuiaInteractor;
import com.business.ventas.utils.PadreInteractor;
import com.business.ventas.utils.PadrePresenter;
import com.business.ventas.utils.PadreView;

public interface DetalleGuiaContract {

    String PRESENTERS = "Presenter";
    String INTERACTOR = "Interactor";

    static <T> T createInstance(Class<T> clas) {
        if (clas.getSimpleName().equalsIgnoreCase(PRESENTERS))
            return null;//(T) new DetalleGuiaPresenter();
        else if (clas.getSimpleName().equalsIgnoreCase(INTERACTOR))
            return (T)new DetalleGuiaInteractor();
        return null;
    }

    interface View extends PadreView {
        void mostrarDetalleComprobante(Comprobante comprobante);
    }

    interface Presenter extends PadrePresenter<Presenter, View> {
        void pedirDetalleComprobante(String codigo, int tipoComprobante);
    }

    interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void pedirDetalleComprobante(String codigo, int tipoComprobante);
    }

}
