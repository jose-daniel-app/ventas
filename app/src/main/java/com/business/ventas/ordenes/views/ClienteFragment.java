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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Cliente;
import com.business.ventas.ordenes.contracts.ClienteContract;
import com.business.ventas.search.SearchToolbar;
import com.business.ventas.search.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ClienteViewAdapter;


import java.util.List;

public class ClienteFragment extends AppFragment implements OnSearchToolbarQueryTextListner, ClienteContract.View {

    VentasLog log = LogFactory.createInstance().setTag(ClienteFragment.class.getSimpleName());

    RecyclerView listaclientes;
    ClienteViewAdapter adapter;
    SearchToolbar searchToolbar;
    ProgressBar progressBar;
    private List<Cliente> clientes;
    ClienteContract.Presenter presenter;

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

        presenter = ClienteContract.createInstance(ClienteContract.Presenter.class)
                .setContext(getMainActivity())
                .setView(this);
        presenter.solicitarlistaClientes();
        mostrarProgresBar(true);
        return view;
    }

    private void loadComponents(View view) {
        listaclientes = view.findViewById(R.id.listacli);
        progressBar = view.findViewById(R.id.progressBar);
        listaclientes.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listaclientes.setLayoutManager(linearLayoutManager);
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

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onQueryTextChange(String editable) {
        // textView.setText(editable);

            adapter.setfilter(new Lista<>(clientes)
                    .filtrar(cliente -> cliente.getNombre().toLowerCase().startsWith(editable.toLowerCase())));



    }

    @Override
    public void onKeyDown(int i, KeyEvent keyEvent) {
        Log.i("backClient", "sdfsdfsdfsf back client");
        //searchToolbar.closeSearchToolbar();
    }

    @Override
    public void mostrarListaClientes(List<Cliente> clientes) {
        adapter = new ClienteViewAdapter(clientes, this::clienteSeleccionado);
        this.clientes = clientes;
        listaclientes.setAdapter(adapter);
        mostrarProgresBar(false);
    }

    private void clienteSeleccionado(Cliente cliente){
        log.info("cliente %s ", cliente.toString());
        getMainActivity().newFragmentHandler().changeFragment(ProductosFragment.newInstance().setCliente(cliente));
        //DialogFullScreenProductos dialog = new DialogFullScreenProductos();
        //FragmentTransaction ft = getFragmentManager().beginTransaction();
        //dialog.show(ft, "dialog");

    }

    @Override
    public void errorRespuesta(String mensaje) {
        log.info("error: %s", mensaje);
        mostrarProgresBar(false);
    }
}
