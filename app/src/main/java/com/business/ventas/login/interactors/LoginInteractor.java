package com.business.ventas.login.interactors;

import com.business.ventas.beans.User;
import com.business.ventas.login.contracts.LoginContract;
import com.business.ventas.repository.RepositoryFactory;
import com.business.ventas.repository.UserRepository;

public class LoginInteractor implements LoginContract.Interactor {

    LoginContract.Presenter presenter;
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
            @Override
            public void succes(User obj) {
                presenter.getView().responseLoginSucces("se uesta los elementos");
            }

            @Override
            public void error(String cause) {
                presenter.getView().responseLoginError("ocurrio un error perro");
            }
        });
    }
}
