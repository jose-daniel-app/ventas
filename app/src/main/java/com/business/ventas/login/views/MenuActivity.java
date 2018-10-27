package com.business.ventas.login.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.business.ventas.R;


public class MenuActivity extends AppCompatActivity {
 //RecyclerView recycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

      //  setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
/*
        recycleView = findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager= layoutManager;

        recycleView.setLayoutManager(rvLiLayoutManager);*/
    }

}
