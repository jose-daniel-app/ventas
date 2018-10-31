package com.business.ventas.login.views;


import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    AuthRepository auth;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = AuthRepository.getInstance();
        loadItems();

    }

    private void loadItems(){
        inputEditTextCorreo = findViewById(R.id.inputEditTextCorreo);
        inputEditTextPassword = findViewById(R.id.inputEditTextPassword);
        button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(R.id.button_login == view.getId()){
            auth.signInWithEmailAndPassword("matias", "12345", this, new AuthRepository.OnCompleteListener() {
                @Override
                public void onComplete(boolean state) {

                }
            });
        }else if(R.id.button_login == view.getId()){
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
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

}
