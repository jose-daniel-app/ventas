package com.business.ventas.repository;

public abstract class RepositoryFactory {

    public static final int API_REST = 1;

    public abstract UserRepository getUserRepository();

    public abstract ProductosRepository getProductosRepository();

    public abstract ClientesRepository getClientesRepository();

    public abstract OrdenesRepository getOrdenesRepository();

    public abstract RutaRepository getRutaRepository();

    public abstract ComprobanteRepository getComprobanteRepository();

    public abstract AlmacenRepository getAlmacenRepository();

    public abstract RequerimientosRepository getRequerimientosRepository();

    public static RepositoryFactory getFactory(int type) {
        switch (type) {
            case API_REST:
                return new RepositoryFactoryImpl();
            default:
                return null;
        }
    }

}
