package com.business.ventas.requerimiento.presenters;

 import android.content.Context;
 import com.business.ventas.requerimiento.contracts.RequerimientoContract;

 public class RequerimientoPresenter implements RequerimientoContract.Presenter {

 	RequerimientoContract.Interactor interactor;
 	RequerimientoContract.View view;
 	Context context;

 	public RequerimientoPresenter() {
 		interactor = RequerimientoContract.newInteractor().setPresenter(this);
 	}

 	@Override
 	public RequerimientoContract.Presenter setView(RequerimientoContract.View view){
 		this.view = view;
 		return this;
 	}

 	@Override
 	public RequerimientoContract.Presenter setContext(Context context) {
 		this.context = context;
 		return this;
 	}

 	@Override
 	public Context getContext() {
 		return context;
 	}

 	@Override
 	public RequerimientoContract.View getView() {
 		return view;
 	}

	 @Override
	 public void solicitarRequerimientos() {
		 this.interactor.solicitarRequerimientos();
	 }
 }
