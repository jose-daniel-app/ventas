package com.business.ventas.ordenes.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Orden;
import com.business.ventas.beans.Producto;
import com.business.ventas.beans.Ruta;
import com.business.ventas.ordenes.contracts.OrdenesContract;
import com.business.ventas.search.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.OrdenesViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class OrdenesFragment extends AppFragment
        implements
        OrdenesContract.View,
        OnSearchToolbarQueryTextListner {

    VentasLog log = LogFactory.createInstance().setTag(OrdenesFragment.class.getSimpleName());
    OrdenesContract.Presenter presenter;

    RecyclerView recyclerViewOrden;
    OrdenesViewAdapter adapter;
    FloatingActionButton addbuton;

    ProgressBar progressBar;


    public OrdenesFragment() {

    }

    public static OrdenesFragment newInstance() {
        return new OrdenesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ordenes, container, false);
        loadComponents(view);
        toolbar.setTitle("Ordenes");
        navigationView.setCheckedItem(R.id.nav_ordenes);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_ordenes);
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        addbuton = view.findViewById(R.id.addbuton);
        addbuton.setOnClickListener(this::ClickActionButon);

        presenter = OrdenesContract.createInstance(OrdenesContract.Presenter.class)
                .setContext(getMainActivity())
                .setView(this);

        presenter.listarOrdenes();
        mostrarProgresBar(true);
        return view;

    }

    private void ClickActionButon(View view) {
        getMainActivity().newFragmentHandler().changeFragment(ClienteFragment.newInstance());
    }

    private void loadComponents(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        recyclerViewOrden = view.findViewById(R.id.recyclerViewOrden);
        recyclerViewOrden.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewOrden.setLayoutManager(linearLayoutManager);
    }


    private boolean onMenuItemClick(MenuItem menuItem) {
        return true;
    }

    private void showDatePickerDialog() {
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
        //super.onKeyDown(i, keyEvent);
        log.info("se apreto el back");
    }

    public void guardarProductostemporales() {
        SharedPreferenceProductos.getInstance().setActivity(getMainActivity()).limpiar();
        SharedPreferenceProductos.getInstance().setActivity(getMainActivity()).guardar(new Lista<Producto>()
                .agregar(new Producto().config()
                        .setNombre("Bizcocho especial x12")
                        .setCantidad(3).setPrecioUnitario(5.0)
                        .actualizarPrecioCantidad()
                        .build())
                .agregar(new Producto().config()
                        .setNombre("Keke ingles x12")
                        .setCantidad(2).setPrecioUnitario(2.0)
                        .actualizarPrecioCantidad()
                        .build())
                .agregar(new Producto().config()
                        .setNombre("Empanada x20")
                        .setCantidad(6).setPrecioUnitario(5.0)
                        .actualizarPrecioCantidad()
                        .build())
        );
    }

    @Override
    public void mostrarOrdenes(List<Orden> ordenes) {
        adapter = new OrdenesViewAdapter(ordenes, this);
        recyclerViewOrden.setAdapter(adapter);
        mostrarProgresBar(false);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        log.error(mensaje);
        mostrarProgresBar(false);
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }
}

