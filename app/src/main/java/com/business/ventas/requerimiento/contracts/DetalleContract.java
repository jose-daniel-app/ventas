package com.business.ventas.requerimiento.contracts;

 import com.business.ventas.beans.Requerimiento;
 import com.business.ventas.requerimiento.interactors.DetalleInteractor;
 import com.business.ventas.requerimiento.presenters.DetallePresenter;
 import com.business.ventas.utils.PadreInteractor;
 import com.business.ventas.utils.PadrePresenter;
 import com.business.ventas.utils.PadreView;

public interface DetalleContract {

 	static Presenter newPresenter(){return new DetallePresenter();}

 	static Interactor newInteractor(){return new DetalleInteractor();}

 	interface View extends PadreView {
 		void mostrarRequerimiento(Requerimiento requerimiento);
 	}

 	interface Presenter extends PadrePresenter<Presenter, View> {
 	    void solicitarDetalleRequerimiento(Requerimiento requerimiento);
 	}

 	interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void solicitarDetalleRequerimiento(Requerimiento requerimiento);
 	}
 }
