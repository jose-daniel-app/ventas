package com.business.ventas.requerimiento.interactors;

 import com.business.ventas.repository.RepositoryFactory;
 import com.business.ventas.repository.RequerimientosRepository;
 import com.business.ventas.requerimiento.contracts.RequerimientoContract;

 public class RequerimientoInteractor implements RequerimientoContract.Interactor {
 	RequerimientoContract.Presenter presenter;
 	RepositoryFactory _factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
 	RequerimientosRepository _requerimiento = _factory.getRequerimientosRepository();

 	@Override
 	public RequerimientoContract.Interactor setPresenter(RequerimientoContract.Presenter presenter) {
 		this.presenter = presenter;
 		return this;
 	}

     @Override
     public void solicitarRequerimientos() {
         _requerimiento.listarRequerimientos(this.presenter.getContext(),
                 this.presenter.getView()::mostrarLosRequerimientos,
                 this.presenter.getView()::errorRespuesta);
     }
 }
