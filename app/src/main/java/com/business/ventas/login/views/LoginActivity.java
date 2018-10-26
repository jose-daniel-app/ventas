package com.business.ventas.login.views;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.business.ventas.R;
import com.business.ventas.login.contracts.LoginContract;
import com.business.ventas.login.contracts.LoginFactory;
import com.business.ventas.repository.AuthRepository;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, AuthRepository.AuthStateListener {

    VentasLog log = LogFactory.createInstance().setTag(LoginActivity.class.getSimpleName());
    AuthRepository auth = AuthRepository.getInstance();
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.presenter = LoginFactory.createInstance(LoginContract.Presenter.class).setView(this);


        log.info("se agrego el log correcta mente y de instancio el presenter");
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        this.presenter.loginSesion("matias","12345");
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

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(boolean state) {
        log.info(" cantidad de veces => " + state);
    }
}
