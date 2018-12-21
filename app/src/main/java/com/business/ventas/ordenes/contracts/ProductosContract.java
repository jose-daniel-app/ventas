package com.business.ventas.ordenes.contracts;

import com.business.ventas.beans.Orden;
import com.business.ventas.beans.Producto;
import com.business.ventas.ordenes.interactors.ProductosInteractor;
import com.business.ventas.ordenes.presenters.ProductosPresenter;
import com.business.ventas.utils.PadreInteractor;
import com.business.ventas.utils.PadrePresenter;
import com.business.ventas.utils.PadreView;

import java.util.List;

public interface ProductosContract {

    String PRESENTERS = "Presenter";
    String INTERACTOR = "Interactor";

    static <T> T createInstance(Class<T> clas) {
        if (clas.getSimpleName().equalsIgnoreCase(PRESENTERS))
            return (T) new ProductosPresenter();
        else if (clas.getSimpleName().equalsIgnoreCase(INTERACTOR))
            return (T) new ProductosInteractor();
        return null;
    }

    interface View extends PadreView {
        void cargarProductos(List<Producto> productos);

        void respuestaCrearOrden(String mensja);
    }

    interface Presenter extends PadrePresenter<Presenter, View> {
        void solicitarProductos();

        void crearNuevaOrden(Orden orden);
    }

    interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void solicitarProductos();

        void crearNuevaOrden(Orden orden);
    }

}
