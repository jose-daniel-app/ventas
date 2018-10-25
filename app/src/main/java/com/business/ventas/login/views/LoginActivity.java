package com.business.ventas.login.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.business.ventas.R;
import com.business.ventas.login.contracts.LoginContract;
import com.business.ventas.login.contracts.LoginFactory;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    VentasLog log = LogFactory.createInstance().setTag(LoginActivity.class.getSimpleName());

    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.presenter = LoginFactory.createInstance(LoginContract.Presenter.class).setView(this);


        log.info("se agrego el log correcta mente y de instancio el presenter");
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void showProgressBar(Boolean show) {

    }

    @Override
    public void responseLoginSucces(String response) {

    }

    @Override
    public void responseLoginError(String response) {

    }
}
