package com.business.ventas.requerimiento.contracts;

import com.business.ventas.beans.Producto;
import com.business.ventas.beans.Requerimiento;
import com.business.ventas.requerimiento.interactors.ReqProductoInteractor;
import com.business.ventas.requerimiento.presenters.ReqProductoPresenter;
import com.business.ventas.utils.PadreInteractor;
import com.business.ventas.utils.PadrePresenter;

import java.util.List;

public interface ReqProductoContract {

    String PRESENTERS = "Presenter";
    String INTERACTOR = "Interactor";

    static <T> T createInstance(Class<T> clas) {
        if (clas.getSimpleName().equalsIgnoreCase(PRESENTERS))
            return (T) new ReqProductoPresenter();
        else if (clas.getSimpleName().equalsIgnoreCase(INTERACTOR))
            return (T) new ReqProductoInteractor();
        return null;
    }

    interface View {
        void cargarProductos(List<Producto> productos);
        void errorRespuesta(String mensaje);
        void respuesDeCrearRequerimiento(Requerimiento requerimiento);

    }
    interface Presenter extends PadrePresenter<Presenter, View> {
        void solicitarProductos();
        void crearRequerimiento(Requerimiento requerimiento);
    }

    interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void solicitarProductos();
        void crearRequerimiento(Requerimiento requerimiento);
    }
}
