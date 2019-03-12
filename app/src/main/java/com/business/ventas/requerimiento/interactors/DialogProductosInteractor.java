package com.business.ventas.requerimiento.interactors;

 import com.business.ventas.beans.Requerimiento;
 import com.business.ventas.repository.ProductosRepository;
 import com.business.ventas.repository.RepositoryFactory;
 import com.business.ventas.repository.RequerimientosRepository;
 import com.business.ventas.requerimiento.contracts.DialogProductosContract;
 import com.business.ventas.utils.Lista;

public class DialogProductosInteractor implements DialogProductosContract.Interactor {
 	DialogProductosContract.Presenter presenter;

     RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
     ProductosRepository _productos = factory.getProductosRepository();
     RequerimientosRepository _requerimiento = factory.getRequerimientosRepository();

 	@Override
 	public DialogProductosContract.Interactor setPresenter(DialogProductosContract.Presenter presenter) {
 		this.presenter = presenter;
 		return this;
 	}

	 @Override
	 public void solicitarProductos() {
         this._productos.listarProductos(presenter.getContext())
                 .setOnRespuestaSucces(productos -> {
                             presenter.getView().cargarProductos(new Lista<>(productos));
                         }
                 ).setOnRespuestaError(presenter.getView()::errorRespuesta);
	 }

	 @Override
	 public void solicitarActualizarRequerimiento(Requerimiento requerimiento) {
         _requerimiento.actualizarRequerimiento(
             presenter.getContext(), requerimiento,
             presenter.getView()::respuestaActualizarRequerimiento,
             presenter.getView()::errorRespuesta
         );
	 }
 }
