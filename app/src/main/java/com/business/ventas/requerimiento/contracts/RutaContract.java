package com.business.ventas.requerimiento.contracts;

import com.business.ventas.beans.Ruta;
import com.business.ventas.requerimiento.interactors.RutaInteractor;
import com.business.ventas.requerimiento.presenters.RutaPresenter;
import com.business.ventas.utils.PadreInteractor;
import com.business.ventas.utils.PadrePresenter;
import com.business.ventas.utils.PadreView;

import java.util.List;

public interface RutaContract {

    String PRESENTERS = "Presenter";
    String INTERACTOR = "Interactor";

    static <T> T createInstance(Class<T> clas) {
        if (clas.getSimpleName().equalsIgnoreCase(PRESENTERS))
            return (T) new RutaPresenter();
        else if (clas.getSimpleName().equalsIgnoreCase(INTERACTOR))
            return (T)new RutaInteractor();
        return null;
    }

    interface View extends PadreView {
        void mostrarRutas(List<Ruta> rutas);
    }

    interface Presenter extends PadrePresenter<Presenter, View> {
        void listarRutas();
    }

    interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void listarRutas();
    }
}
