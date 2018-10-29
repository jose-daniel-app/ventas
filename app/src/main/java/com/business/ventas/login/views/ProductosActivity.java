package com.business.ventas.login.views;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import com.business.ventas.R;

import com.business.ventas.repository.AuthRepository;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

public class ProductosActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
private ActionBarDrawerToggle mToggle;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_productos);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_menu);
            mToggle =new ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close);
drawerLayout.addDrawerListener(mToggle);
mToggle.syncState();
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
@Override
public boolean onOptionsItemSelected(MenuItem item){
            return super.onOptionsItemSelected(item);
}
}
