package com.business.ventas.ordenes.interactors;

import com.business.ventas.beans.Orden;
import com.business.ventas.beans.Producto;
import com.business.ventas.ordenes.contracts.ProductosContract;
import com.business.ventas.repository.AlmacenRepository;
import com.business.ventas.repository.OrdenesRepository;
import com.business.ventas.repository.ProductosRepository;
import com.business.ventas.repository.RepositoryFactory;

public class ProductosInteractor implements ProductosContract.Interactor {

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    ProductosRepository _producto = factory.getProductosRepository();
    OrdenesRepository _orden = factory.getOrdenesRepository();
    AlmacenRepository _almacen = factory.getAlmacenRepository();
    ProductosContract.Presenter presenter;

    @Override
    public ProductosContract.Interactor setPresenter(ProductosContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }

    @Override
    public void solicitarProductos(String nombreAlmacen) {

        _almacen.obtenerAlmacen(presenter.getContext(), nombreAlmacen, almacen -> {
            _producto.listarProductos(presenter.getContext())
                    .setOnRespuestaSucces(listaProductos -> {
                        for (Producto p : listaProductos)
                            p.setAlmacen(almacen);
                        presenter.getView().cargarProductos(listaProductos);
                    })
                    .setOnRespuestaError(presenter.getView()::errorRespuesta);
        }, presenter.getView()::errorRespuesta);

        /*_producto.listarProductos(presenter.getContext())
            .setOnRespuestaSucces(presenter.getView()::cargarProductos)
            .setOnRespuestaError(presenter.getView()::errorRespuesta);*/
    }

    @Override
    public void crearNuevaOrden(Orden orden) {
        _orden.crearOrden(
            presenter.getContext(), orden,
            presenter.getView()::respuestaCrearOrden,
            presenter.getView()::errorRespuesta
        );
    }
}
