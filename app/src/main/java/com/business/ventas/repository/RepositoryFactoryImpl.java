package com.business.ventas.repository;

public class RepositoryFactoryImpl extends RepositoryFactory {
    @Override
    public UserRepository getUserRepository() {
        return new UserRepositoryImpl();
    }

    @Override
    public ProductosRepository getProductosRepository() {
        return new ProductosRepositoryImpl();
    }
}
