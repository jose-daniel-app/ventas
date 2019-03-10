package com.business.ventas.requerimiento.contracts;

 import com.business.ventas.beans.Requerimiento;
 import com.business.ventas.requerimiento.interactors.RequerimientoInteractor;
 import com.business.ventas.requerimiento.presenters.RequerimientoPresenter;
 import com.business.ventas.utils.PadreInteractor;
 import com.business.ventas.utils.PadrePresenter;
 import com.business.ventas.utils.PadreView;

 import java.util.List;

public interface RequerimientoContract {

 	static Presenter newPresenter(){return new RequerimientoPresenter();}

 	static Interactor newInteractor(){return new RequerimientoInteractor();}

 	interface View extends PadreView {
 		void mostrarLosRequerimientos(List<Requerimiento> requerimientos);
 	}

 	interface Presenter extends PadrePresenter<Presenter, View> {
 	    void solicitarRequerimientos();
 	}

 	interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void solicitarRequerimientos();
 	}
 }
