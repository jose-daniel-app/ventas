package com.business.ventas.login.contracts;

import com.business.ventas.login.presenters.LoginPresenters;

public abstract class LoginFactory {

    private static final String CONTRACTS_PRESENTERS = "Presenter";

    public static <T> T createInstance(Class<T> clas){

        if(clas.getSimpleName().equalsIgnoreCase(CONTRACTS_PRESENTERS))
            return (T) new LoginPresenters();
        return null;
    }

}
