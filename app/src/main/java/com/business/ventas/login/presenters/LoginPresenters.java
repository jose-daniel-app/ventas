package com.business.ventas.login.presenters;

import android.content.Context;

import com.business.ventas.login.contracts.LoginContract;

public class LoginPresenters implements LoginContract.Presenter {

    LoginContract.View view;

    @Override
    public LoginContract.Presenter setView(Context context) {
        view = (LoginContract.View) context;
        return this;
    }
}
