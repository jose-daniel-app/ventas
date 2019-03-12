package com.business.ventas.requerimiento.contracts;

 import com.business.ventas.beans.Producto;
 import com.business.ventas.beans.Requerimiento;
 import com.business.ventas.requerimiento.interactors.DialogProductosInteractor;
 import com.business.ventas.requerimiento.presenters.DialogProductosPresenter;
 import com.business.ventas.utils.Lista;
 import com.business.ventas.utils.PadreInteractor;
 import com.business.ventas.utils.PadrePresenter;
 import com.business.ventas.utils.PadreView;

public interface DialogProductosContract {

 	static Presenter newPresenter(){return new DialogProductosPresenter();}

 	static Interactor newInteractor(){return new DialogProductosInteractor();}

 	interface View extends PadreView {
        void cargarProductos(Lista<Producto> productos);
        void respuestaActualizarRequerimiento(Requerimiento requerimiento);
 	}

 	interface Presenter extends PadrePresenter<Presenter, View> {
        void solicitarProductos();
        void solicitarActualizarRequerimiento(Requerimiento requerimiento);
 	}

 	interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void solicitarProductos();
        void solicitarActualizarRequerimiento(Requerimiento requerimiento);
 	}
 }
