package com.business.ventas.requerimiento.presenters;

 import android.content.Context;

 import com.business.ventas.beans.Requerimiento;
 import com.business.ventas.requerimiento.contracts.DialogProductosContract;

 public class DialogProductosPresenter implements DialogProductosContract.Presenter {

 	DialogProductosContract.Interactor interactor;
 	DialogProductosContract.View view;
 	Context context;

 	public DialogProductosPresenter() {
 		interactor = DialogProductosContract.newInteractor().setPresenter(this);
 	}

 	@Override
 	public DialogProductosContract.Presenter setView(DialogProductosContract.View view){
 		this.view = view;
 		return this;
 	}

 	@Override
 	public DialogProductosContract.Presenter setContext(Context context) {
 		this.context = context;
 		return this;
 	}

 	@Override
 	public Context getContext() {
 		return context;
 	}

 	@Override
 	public DialogProductosContract.View getView() {
 		return view;
 	}

     @Override
     public void solicitarProductos() {
         this.interactor.solicitarProductos();
     }

     @Override
     public void solicitarActualizarRequerimiento(Requerimiento requerimiento) {
        this.interactor.solicitarActualizarRequerimiento(requerimiento);
     }
 }
