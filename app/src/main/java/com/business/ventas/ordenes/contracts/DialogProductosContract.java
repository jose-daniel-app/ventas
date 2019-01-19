package com.business.ventas.ordenes.contracts;

import com.business.ventas.beans.Orden;
import com.business.ventas.beans.Producto;
import com.business.ventas.ordenes.interactors.DialogProductosInteractor;
import com.business.ventas.ordenes.presenters.DialogProductosPresenter;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.PadreInteractor;
import com.business.ventas.utils.PadrePresenter;
import com.business.ventas.utils.PadreView;

public interface DialogProductosContract {

    String PRESENTERS = "Presenter";
    String INTERACTOR = "Interactor";

    static <T> T createInstance(Class<T> clas) {
        if (clas.getSimpleName().equalsIgnoreCase(PRESENTERS))
            return (T)new DialogProductosPresenter();
        else if (clas.getSimpleName().equalsIgnoreCase(INTERACTOR))
            return (T)new DialogProductosInteractor();
        return null;
    }

    interface View extends PadreView {
        void cargarProductos(Lista<Producto> productos);
        void respuestaActualizarOrden(Orden orden);
    }

    interface Presenter extends PadrePresenter<Presenter, View> {
        void solicitarProductos();
        void solicitarActualizarOrden(Orden orden);
    }

    interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void solicitarProductos();
        void solicitarActualizarOrden(Orden orden);
    }

}
