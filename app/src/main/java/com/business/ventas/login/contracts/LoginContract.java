package com.business.ventas.login.contracts;

import android.content.Context;

public interface LoginContract {

    interface View {
        void showProgressBar(Boolean show);
        void responseLoginSucces(String response);
        void responseLoginError(String response);
    }

    interface Presenter {
        Presenter setView(Context context);
        void loginSesion(String correo,String password);
        View getView();
    }

    interface Interactor {
        Interactor setPresenter(Presenter presenter);
        void loginSesion(String correo,String password);
    }

}
