package com.business.ventas.login.interactors;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.business.ventas.beans.User;
import com.business.ventas.login.contracts.LoginContract;
import com.business.ventas.repository.AuthRepository;
import com.business.ventas.repository.RepositoryFactory;
import com.business.ventas.repository.UserRepository;

public class LoginInteractor implements LoginContract.Interactor {

    LoginContract.Presenter presenter;

    @Override
    public LoginContract.Interactor setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }

}
