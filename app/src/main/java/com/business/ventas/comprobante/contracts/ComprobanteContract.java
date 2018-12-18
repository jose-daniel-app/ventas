package com.business.ventas.comprobante.contracts;

import com.business.ventas.beans.Comprobante;
import com.business.ventas.comprobante.interactors.ComprobanteInteractor;
import com.business.ventas.comprobante.presenters.ComprobantePresenter;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.PadreInteractor;
import com.business.ventas.utils.PadrePresenter;
import com.business.ventas.utils.PadreView;

public interface ComprobanteContract {

    String PRESENTERS = "Presenter";
    String INTERACTOR = "Interactor";

    static <T> T createInstance(Class<T> clas) {
        if (clas.getSimpleName().equalsIgnoreCase(PRESENTERS))
            return (T) new ComprobantePresenter();
        else if (clas.getSimpleName().equalsIgnoreCase(INTERACTOR))
            return (T)new ComprobanteInteractor();
        return null;
    }

    interface View extends PadreView {
        void mostrarComprovantes(Lista<Comprobante> comprobantes);
    }

    interface Presenter extends PadrePresenter<Presenter, View> {
        void pedirComprobantes();
    }

    interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void pedirComprobantes();
    }

}
