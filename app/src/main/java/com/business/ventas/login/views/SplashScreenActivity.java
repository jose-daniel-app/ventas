package com.business.ventas.login.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.business.ventas.repository.AuthRepository;

public class SplashScreenActivity extends AppCompatActivity implements AuthRepository.AuthStateListener{

    AuthRepository auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = AuthRepository.newInstance();
        auth.addAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(boolean estado) {
        if(estado){
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
