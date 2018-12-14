package com.business.ventas.repository;

public class RepositoryFactoryImpl extends RepositoryFactory {

    @Override
    public OrdenesRepository getOrdenesRepository() {
        return new OrdenesRepositoryImpl();
    }

    @Override
    public RutaRepository getRutaRepository() {
        return new RutaRepositoryImpl();
    }

    @Override
    public ClientesRepository getClientesRepository() {
        return new ClientesRepositoryImpl();
    }

    @Override
    public UserRepository getUserRepository() {
        return new UserRepositoryImpl();
    }

    @Override
    public ProductosRepository getProductosRepository() {
        return new ProductosRepositoryImpl();
    }
}
