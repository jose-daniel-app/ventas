package com.business.ventas.login.views;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
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
    Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.presenter = LoginFactory.createInstance(LoginContract.Presenter.class).setView(this);
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadItems();

/*
        button_login =(Button)findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button_login= new Intent(LoginActivity.this,MenuActivity.class);
                startActivity(button_login);

            }
        });*/
    }

    private void loadItems(){
        inputEditTextCorreo = findViewById(R.id.inputEditTextCorreo);
        inputEditTextPassword = findViewById(R.id.inputEditTextPassword);
<<<<<<< HEAD
        material_buttond = findViewById(R.id.material_buttond);
        material_buttond.setOnClickListener(this);
=======
        button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(this);

>>>>>>> origin/master
    }

    @Override
    public void onClick(View view) {
<<<<<<< HEAD
        if(R.id.material_buttond == view.getId()){
            auth.signInWithEmailAndPassword("matias", "12345", this, new AuthRepository.OnCompleteListener() {
                @Override
                public void onComplete(boolean state) {

                }
            });
        }else if(R.id.btnCerrarsesion == view.getId()){
            auth.signOut(this);
        }


    }

    @Override
    public void showProgressBar(Boolean show) {

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
