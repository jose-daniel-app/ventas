package com.business.ventas.login.contracts;

import android.content.Context;

public interface LoginContract {

    interface View {
        void showProgressBar(Boolean show);
    }

    interface Presenter {
        Presenter setView(Context context);
        View getView();
    }

    interface Interactor {
        Interactor setPresenter(Presenter presenter);
    }

}
