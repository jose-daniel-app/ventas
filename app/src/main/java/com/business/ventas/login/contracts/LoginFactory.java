package com.business.ventas.login.contracts;

import com.business.ventas.login.interactors.LoginInteractor;
import com.business.ventas.login.presenters.LoginPresenter;

public abstract class LoginFactory {

    private static final String CONTRACTS_PRESENTERS = "Presenter";
    private static final String CONTRACTS_INTERACTOR = "Interactor";

    public static <T> T createInstance(Class<T> clas){

        if(clas.getSimpleName().equalsIgnoreCase(CONTRACTS_PRESENTERS))
            return (T) new LoginPresenter();
        if(clas.getSimpleName().equalsIgnoreCase(CONTRACTS_INTERACTOR))
            return (T) new LoginInteractor();
        return null;
    }

}
