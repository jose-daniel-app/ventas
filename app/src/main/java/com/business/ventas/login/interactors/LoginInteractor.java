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
    AuthRepository authRepository = AuthRepository.getInstance();

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    UserRepository userRepository = factory.getUserRepository();


    @Override
    public LoginContract.Interactor setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }

    @Override
    public void loginSesion(String correo, String password) {

        userRepository.loginSesion(correo, password, new UserRepository.Respond<User>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void succes(User obj) {
                authRepository.addKey(obj.getApiKey(),(Context) presenter.getView());
                presenter.getView().responseLoginSucces("Se inicio correctamente");
            }

            @Override
            public void error(String cause) {
                presenter.getView().responseLoginError(cause);
            }
        });
    }
}
