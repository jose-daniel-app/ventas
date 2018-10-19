package com.business.ventas.login.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.business.ventas.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         *
         *
         *
         * */
        try{
            Thread.sleep(2000);
        }catch (Exception e){

        }

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

}
