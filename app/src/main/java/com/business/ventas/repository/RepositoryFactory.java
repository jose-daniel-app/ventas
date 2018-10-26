package com.business.ventas.repository;

public abstract class RepositoryFactory {

    public static final int API_REST = 1;

    public abstract UserRepository getUserRepository();

    public static RepositoryFactory getFactory(int type){
        switch (type){
            case API_REST: return new RepositoryFactoryImpl();
            default: return null;
        }
    }

}
