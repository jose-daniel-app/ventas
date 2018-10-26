package com.business.ventas.login.presenters;

import android.content.Context;

import com.business.ventas.login.contracts.LoginContract;
import com.business.ventas.login.contracts.LoginFactory;

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;
    LoginContract.Interactor interactor;

    @Override
    public LoginContract.Presenter setView(Context context) {
        this.interactor = LoginFactory.createInstance(LoginContract.Interactor.class).setPresenter(this);
        this.view = (LoginContract.View) context;
        return this;
    }

    @Override
    public void loginSesion(String correo, String password) {
        this.interactor.loginSesion(correo,password);
    }

    @Override
    public LoginContract.View getView(){
        return view;
    }
}
