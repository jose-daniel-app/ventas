package com.business.ventas.login.views;

import android.arch.lifecycle.ViewModel;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.business.ventas.R;
import com.business.ventas.login.contracts.LoginContract;
import com.business.ventas.login.contracts.LoginFactory;
import com.business.ventas.repository.AuthRepository;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

public class LoginActivity extends AppCompatActivity implements
        LoginContract.View, AuthRepository.AuthStateListener, View.OnClickListener {

    VentasLog log = LogFactory.createInstance().setTag(LoginActivity.class.getSimpleName());
    AuthRepository auth = AuthRepository.getInstance();
    LoginContract.Presenter presenter;

    /*
    * Declaracion de elementos
    * */
    TextInputEditText inputEditTextCorreo;
    TextInputEditText inputEditTextPassword;
    Button material_buttond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.presenter = LoginFactory.createInstance(LoginContract.Presenter.class).setView(this);
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadItems();
    }

    private void loadItems(){
        inputEditTextCorreo = findViewById(R.id.inputEditTextCorreo);
        inputEditTextPassword = findViewById(R.id.inputEditTextPassword);
        material_buttond = findViewById(R.id.material_buttond);
        material_buttond.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(R.id.material_buttond == view.getId()){
            this.presenter.loginSesion("matias","12345");
        }else if(R.id.btnCerrarsesion == view.getId()){
            auth.deleteKey(this);
        }
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
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(boolean state) {
        if(state){
            log.info("session activa");
        }else {
            log.info("session cerrada");
        }
    }

}
