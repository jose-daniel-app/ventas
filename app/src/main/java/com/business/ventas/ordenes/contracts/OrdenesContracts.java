package com.business.ventas.ordenes.contracts;

import android.content.Context;

import com.business.ventas.beans.Producto;
import com.business.ventas.ordenes.interactors.OrdenesInteractor;
import com.business.ventas.ordenes.presenters.OrdenesPresenter;

import java.util.List;

public interface OrdenesContracts {

    String PRESENTERS = "Presenter";
    String INTERACTOR = "Interactor";

    static <T> T createInstance(Class<T> clas) {
        if (clas.getSimpleName().equalsIgnoreCase(PRESENTERS))
            return (T) new OrdenesPresenter();
        else if (clas.getSimpleName().equalsIgnoreCase(INTERACTOR))
            return (T) new OrdenesInteractor();
        return null;
    }

    interface View {
        void showProgressBar(Boolean show);
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
