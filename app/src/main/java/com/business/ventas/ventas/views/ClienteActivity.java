package com.business.ventas.ventas.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Cliente;

import java.util.ArrayList;
import java.util.List;


public class ClienteActivity extends AppCompatActivity {
    SearchView searchView;
    RecyclerView listaclientes;
    List<Cliente> productlists = new ArrayList<>();

    ClienteActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        showToolbar("Clientes",false);

        productlists.add(new Cliente("Ana Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Beto Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Carlos Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("David Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Eduardo Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Fernando Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Giovanni Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Helena Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Isabel Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Jose Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));


        listaclientes = (RecyclerView) findViewById(R.id.listacli);
        listaclientes.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaclientes.setLayoutManager(linearLayoutManager);

        adapter = new ClienteActivityAdapter(productlists, this);
        listaclientes.setAdapter(adapter);
    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(tittle);
        TextView TexToolbarTextViewTitle = findViewById(R.id.toolbarTextViewTitle);
        TexToolbarTextViewTitle.setText(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
