package com.business.ventas.login.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.business.ventas.R;
import com.business.ventas.repository.AuthRepository;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;


public class MenuActivity extends AppCompatActivity implements
        AuthRepository.AuthStateListener,  View.OnClickListener {

    AuthRepository auth = AuthRepository.getInstance();;
    VentasLog log  = LogFactory.createInstance().setTag(MenuActivity.class.getSimpleName());;
    /**
     * Declaraciones de Items
     * */
    CardView cardviewPrueba2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        loadItems();
    }

    private void loadItems(){
        cardviewPrueba2 = findViewById(R.id.cardviewPrueba2);
        cardviewPrueba2.setOnClickListener(this);
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
        if(!state){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }


    @Override
    public void onClick(View view) {
        if(R.id.cardviewPrueba2 == view.getId()){
            auth.signOut(this);
        }
    }
}
