package com.business.ventas.ordenes.contracts;

import com.business.ventas.beans.Producto;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.PadreInteractor;
import com.business.ventas.utils.PadrePresenter;
import com.business.ventas.utils.PadreView;

public interface DialogProductosContract {

    String PRESENTERS = "Presenter";
    String INTERACTOR = "Interactor";

    static <T> T createInstance(Class<T> clas) {
        if (clas.getSimpleName().equalsIgnoreCase(PRESENTERS))
            return null;//(T) new OrdenesPresenter();
        else if (clas.getSimpleName().equalsIgnoreCase(INTERACTOR))
            return null;//(T)new OrdenesInteractor();
        return null;
    }

    interface View extends PadreView {
        void cargarProductos(Lista<Producto> productos);
    }

    interface Presenter extends PadrePresenter<Presenter, View> {
        void solicitarProductos();
    }

    interface Interactor extends PadreInteractor<Interactor, Presenter> {

    }

}
