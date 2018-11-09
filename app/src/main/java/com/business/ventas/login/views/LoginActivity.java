package com.business.ventas.login.views;


import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.login.contracts.LoginContract;
import com.business.ventas.login.contracts.LoginFactory;
import com.business.ventas.repository.AuthRepository;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements
        LoginContract.View, AuthRepository.AuthStateListener, View.OnClickListener {

    VentasLog log = LogFactory.createInstance().setTag(LoginActivity.class.getSimpleName());
    AuthRepository auth;
    LoginContract.Presenter presenter;

    private static final Pattern PASSWORD_PATTERN= Pattern.compile("^" +
            "(?=.*[0-9])" +         //al menos 1 dijito
            //"(?=.*[a-z])" +         // al menos 1 miniscula
            //"(?=.*[A-Z])" +         //al menos 1 mayuscola
            //  "(?=.*[a-zA-Z])" +      //cualquier letra
            //   "(?=.*[@#$%^&+=])" +    //al menos un caracter especial
            "(?=\\S+$)" +           //sin espacios
            ".{4,}" +               //al menos 4 caracteres
            "$");

    /*
    * Declaracion de elementos
    * */
    TextInputEditText inputEditTextCorreo;
    TextInputEditText inputEditTextPassword;
    Button button_login;
    ProgressBar progressBar;
    TextInputLayout inputTextLayoutCorreo;
    TextInputLayout inputTextLayoutPassword;


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
        progressBar = findViewById(R.id.progressBar);
        button_login.setOnClickListener(this);
        inputTextLayoutCorreo = findViewById(R.id.inputTextLayoutCorreo);
        inputTextLayoutPassword = findViewById(R.id.inputTextLayoutPassword);

    }

    @Override
    public void onClick(View view) {

        String correo = inputEditTextCorreo.getText().toString();
        String password = inputEditTextPassword.getText().toString();

        //-------------mensaje de confirmacion -------------------
        if (!validarCorreo() | !validarPassword() ) {
            return;
        }
        Toast.makeText(this,"ingreso exitoso"+correo,Toast.LENGTH_LONG).show();
        //-----------------------------------------------------------


        if(R.id.button_login == view.getId()){
            showProgressBar(true);
            auth.signInWithEmailAndPassword(correo, password, this, new AuthRepository.OnCompleteListener() {
                @Override
                public void onComplete(boolean state) {
                    showProgressBar(false);
                }
            });
        }else if(R.id.button_login == view.getId()){
            auth.signOut(this);
        }

    }

    @Override
    public void showProgressBar(Boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
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



    //validacion correo//
    public boolean validarCorreo() {
        String inputCorreo = inputEditTextCorreo.getText().toString().trim(); //comprueba el edit text
        if (inputCorreo.isEmpty()) {
            inputTextLayoutCorreo.setError("Por favor, ingresar correo"); //mensaje al layout
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputCorreo).matches()) {
            inputTextLayoutCorreo.setError("Caracteres incorrectos");
            return false;

        } else {
            inputTextLayoutCorreo.setError(null);
            return true;
        }
    }
    //validar password//
    public boolean validarPassword(){
        String inputPassword = inputEditTextPassword.getText().toString().trim();

        if (inputPassword.isEmpty()) {
            inputTextLayoutPassword.setError("Por favor, ingresar contraseña");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(inputPassword).matches()) {
            inputTextLayoutPassword.setError("Contraseña debe ser mayor de 4 numeros");
            return false;
        } else {
            inputTextLayoutPassword.setError(null);
            return true;
        }

    }

}
