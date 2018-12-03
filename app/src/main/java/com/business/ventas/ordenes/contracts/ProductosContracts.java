package com.business.ventas.ordenes.contracts;

import android.content.Context;

import com.business.ventas.beans.Producto;
import com.business.ventas.ordenes.interactors.ProductosInteractor;
import com.business.ventas.ordenes.presenters.ProductosPresenter;

import java.util.List;

public interface ProductosContracts {

    String PRESENTERS = "Presenter";
    String INTERACTOR = "Interactor";

    static <T> T createInstance(Class<T> clas) {
        if (clas.getSimpleName().equalsIgnoreCase(PRESENTERS))
            return (T) new ProductosPresenter();
        else if (clas.getSimpleName().equalsIgnoreCase(INTERACTOR))
            return (T) new ProductosInteractor();
        return null;
    }

    interface View {
        void errorRespuesta(String mensaje);
        void cargarProductos(List<Producto> productos);
    }

    interface Presenter {
        Presenter setView(View context);
        Presenter SetContext(Context context);
        View getView();
        Context getContext();
        void solicitarProductos();
    }

    interface Interactor {
        Interactor setPresenter(Presenter presenter);
        void solicitarProductos();
    }

}
