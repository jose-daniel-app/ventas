package com.business.ventas.ventas.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.business.ventas.R;

import com.business.ventas.repository.AuthRepository;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

public class ProductosActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        showToolbar("Productos", false);
    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(tittle);
        //TextView TexToolbarTextViewTitle = findViewById(R.id.toolbarTextViewTitle);
        //TexToolbarTextViewTitle.setText(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState(); */

    }


}
