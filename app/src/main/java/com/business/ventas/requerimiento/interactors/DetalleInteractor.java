package com.business.ventas.requerimiento.interactors;

 import com.business.ventas.beans.Requerimiento;
 import com.business.ventas.repository.RepositoryFactory;
 import com.business.ventas.repository.RequerimientosRepository;
 import com.business.ventas.requerimiento.contracts.DetalleContract;

 public class DetalleInteractor implements DetalleContract.Interactor {
 	DetalleContract.Presenter presenter;

 	RepositoryFactory _factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
 	RequerimientosRepository _reqRepository = _factory.getRequerimientosRepository();

 	@Override
 	public DetalleContract.Interactor setPresenter(DetalleContract.Presenter presenter) {
 		this.presenter = presenter;
 		return this;
 	}

     @Override
     public void solicitarDetalleRequerimiento(Requerimiento requerimiento) {
         this._reqRepository.obtenerRequerimiento(
             this.presenter.getContext(),requerimiento,
             this.presenter.getView()::mostrarRequerimiento,
             this.presenter.getView()::errorRespuesta
         );
     }
 }
