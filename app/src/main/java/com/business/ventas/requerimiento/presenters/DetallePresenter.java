package com.business.ventas.requerimiento.presenters;

 import android.content.Context;

 import com.business.ventas.beans.Requerimiento;
 import com.business.ventas.requerimiento.contracts.DetalleContract;

 public class DetallePresenter implements DetalleContract.Presenter {

 	DetalleContract.Interactor interactor;
 	DetalleContract.View view;
 	Context context;

 	public DetallePresenter() {
 		interactor = DetalleContract.newInteractor().setPresenter(this);
 	}

 	@Override
 	public DetalleContract.Presenter setView(DetalleContract.View view){
 		this.view = view;
 		return this;
 	}

 	@Override
 	public DetalleContract.Presenter setContext(Context context) {
 		this.context = context;
 		return this;
 	}

 	@Override
 	public Context getContext() {
 		return context;
 	}

 	@Override
 	public DetalleContract.View getView() {
 		return view;
 	}

     @Override
     public void solicitarDetalleRequerimiento(Requerimiento requerimiento) {
         this.interactor.solicitarDetalleRequerimiento(requerimiento);
     }
 }
