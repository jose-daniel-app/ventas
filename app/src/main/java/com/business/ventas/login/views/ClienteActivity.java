package com.business.ventas.login.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
    List<Cliente> productlists =new ArrayList<>();

    ClienteActivityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        productlists.add(new Cliente("Ana Nombre Apellido ",R.drawable.logobase1));
        productlists.add(new Cliente("Beto Nombre Apellido ",R.drawable.logobase1));
        productlists.add(new Cliente("Carlos Nombre Apellido ",R.drawable.logobase1));
        productlists.add(new Cliente("David Nombre Apellido ",R.drawable.logobase1));
        productlists.add(new Cliente("Eduardo Nombre Apellido ",R.drawable.logobase1));
        productlists.add(new Cliente("Fernando Nombre Apellido ",R.drawable.logobase1));
        productlists.add(new Cliente("Giovanni Nombre Apellido ",R.drawable.logobase1));
        productlists.add(new Cliente("Helena Nombre Apellido ",R.drawable.logobase1));
        productlists.add(new Cliente("Isabel Nombre Apellido ",R.drawable.logobase1));
        productlists.add(new Cliente("Jose Nombre Apellido ",R.drawable.logobase1));


        listaclientes=(RecyclerView) findViewById(R.id.listacli);
        listaclientes.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaclientes.setLayoutManager(linearLayoutManager);

        adapter= new ClienteActivityAdapter(productlists,ClienteActivity.this);
        listaclientes.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchfile,menu);

        final MenuItem myActionMenuItem=menu.findItem(R.id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(searchView);

        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.icons));
searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){


                                      @Override
                                      public boolean onQueryTextSubmit(String query) {
                                        if (!searchView.isIconified()){
                                            searchView.setIconified(true);
                                        }
                                        myActionMenuItem.collapseActionView();
                                        return false;
                                      }

                                      @Override
                                      public boolean onQueryTextChange(String newText) {
                                          final List<Cliente> filtermodelist=filter(productlists,newText );
                                          adapter.setfilter(filtermodelist);
                                          return false;
                                      }
                                  });

        return true;
    }

    private List<Cliente> filter (List<Cliente> cl, String query)
    {
        query=query.toLowerCase();
        final List<Cliente> filteredModeList=new ArrayList<>();
        for(Cliente model: cl)
        {
            final String text=model.getNombre().toLowerCase();
            if (text.startsWith(query))
            {
                filteredModeList.add(model);
            }

        }
return filteredModeList;
    }

    //color search view
private void changeSearchViewTextColor(View view) {
        if (view !=null) {
            if(view instanceof TextView) {
                ((TextView)view).setTextColor(Color.WHITE);
                return;
            }else if (view instanceof ViewGroup){
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i< viewGroup.getChildCount();i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }

            }

        }

}


}
