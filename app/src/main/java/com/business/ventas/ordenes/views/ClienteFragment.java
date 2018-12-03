package com.business.ventas.ordenes.views;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Cliente;
import com.business.ventas.search.SearchToolbar;
import com.business.ventas.search.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.viewAdapter.ClienteViewAdapter;


import java.util.ArrayList;
import java.util.List;

public class ClienteFragment extends AppFragment implements OnSearchToolbarQueryTextListner {

    RecyclerView listaclientes;
    List<Cliente> productlists = new ArrayList<>();
    ClienteViewAdapter adapter;
    SearchToolbar searchToolbar;

    public ClienteFragment() {
    }

    public static ClienteFragment newInstance() {
        return new ClienteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);
        loadComponents(view);
        toolbar.setTitle(R.string.title_cliente);
        navigationView.setCheckedItem(R.id.nav_ordenes);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_cliente);
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        searchToolbar = new SearchToolbar(getActivity(), this, getActivity().findViewById(R.id.search_layout));
        return view;
    }

    private void loadComponents(View view) {

        if (productlists.size() == 0) {
            productlists.add(new Cliente("Ana Nombre Apellido ", R.drawable.ic_account_circle_black_24dp, "104593895087", "SMP, urb. los cedros de naranjal"));
            productlists.add(new Cliente("Beto Nombre Apellido ", R.drawable.ic_account_circle_black_24dp, "104593895087", "SMP, urb. los cedros de naranjal"));
            productlists.add(new Cliente("Carlos Nombre Apellido ", R.drawable.ic_account_circle_black_24dp, "104593895087", "SMP, urb. los cedros de naranjal"));
            productlists.add(new Cliente("David Nombre Apellido ", R.drawable.ic_account_circle_black_24dp, "104593895087", "SMP, urb. los cedros de naranjal"));
            productlists.add(new Cliente("Eduardo Nombre Apellido ", R.drawable.ic_account_circle_black_24dp, "104593895087", "SMP, urb. los cedros de naranjal"));
            productlists.add(new Cliente("Fernando Nombre Apellido ", R.drawable.ic_account_circle_black_24dp, "104593895087", "SMP, urb. los cedros de naranjal"));
            productlists.add(new Cliente("Giovanni Nombre Apellido ", R.drawable.ic_account_circle_black_24dp, "104593895087", "SMP, urb. los cedros de naranjal"));
            productlists.add(new Cliente("Helena Nombre Apellido ", R.drawable.ic_account_circle_black_24dp, "104593895087", "SMP, urb. los cedros de naranjal"));
            productlists.add(new Cliente("Isabel Nombre Apellido ", R.drawable.ic_account_circle_black_24dp, "104593895087", "SMP, urb. los cedros de naranjal"));
            productlists.add(new Cliente("Jose Nombre Apellido ", R.drawable.ic_account_circle_black_24dp, "104593895087", "SMP, urb. los cedros de naranjal"));
        }


        listaclientes = view.findViewById(R.id.listacli);
        listaclientes.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listaclientes.setLayoutManager(linearLayoutManager);

        adapter = new ClienteViewAdapter(productlists, this);
        listaclientes.setAdapter(adapter);
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.ic_search:
                searchToolbar.openSearchToolbar();
                break;
        }
        return true;
    }

    public void onQueryTextSubmit(String query) {
        Toast.makeText(getActivity(), "User Query: " + query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryTextChange(String editable) {
        // textView.setText(editable);
    }

    @Override
    public void onKeyDown(int i, KeyEvent keyEvent) {
        Log.i("backClient","sdfsdfsdfsf back client");
        //searchToolbar.closeSearchToolbar();
    }
}
