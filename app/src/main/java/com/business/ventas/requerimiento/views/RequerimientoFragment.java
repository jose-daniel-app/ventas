package com.business.ventas.requerimiento.views;


import com.business.ventas.beans.Producto;
import com.business.ventas.login.contracts.LoginContract;
import com.business.ventas.login.views.SearchToolbar;

import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Requerimiento;
import com.business.ventas.login.views.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.RequerimientoViewAdapter;


import java.util.ArrayList;
import java.util.List;


public class RequerimientoFragment extends AppFragment implements OnSearchToolbarQueryTextListner {

    VentasLog log = LogFactory.createInstance().setTag(RequerimientoFragment.class.getSimpleName());

    RecyclerView listarequerimientos;
    List<Requerimiento> productlists = new ArrayList<>();
    RequerimientoViewAdapter adapter;

    FloatingActionButton floatingActionButtonAgregar;

    public RequerimientoFragment() {
        // Required empty public constructor
    }

    public static RequerimientoFragment newInstance() {
        return new RequerimientoFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requerimiento, container, false);
        loadComponents(view);
        toolbar.setTitle(R.string.title_requerimiento);
        navigationView.setCheckedItem(R.id.nav_ventas);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_requerimiento);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_date_range));
        return view;
    }

    private void btnCrearRequerimiento(View view) {
        //this.setTipoAccion(CREAR_REQUERIMIENTO);
        onButtonPressed(this);
    }

    private void loadComponents(View view) {

        floatingActionButtonAgregar = view.findViewById(R.id.fbAgregarRuta);
        floatingActionButtonAgregar.setOnClickListener(this::btnCrearRequerimiento);

        if (productlists.size() == 0) {
            productlists.add(new Requerimiento("R003QW ", "10/10/2018 10:10:23", "10/10/2018 20:10:23", "RT0467"));
            productlists.add(new Requerimiento("R004QW ", "10/10/2018 10:10:23", "10/10/2018 20:10:23", "RT0467"));
            productlists.add(new Requerimiento("R005QW ", "10/10/2018 12:10:23", "10/10/2018 20:10:23", "RT0467"));
            productlists.add(new Requerimiento("R006QW ", "10/10/2018 13:10:23", "10/10/2018 20:10:23", "RT0467"));
            productlists.add(new Requerimiento("R007QW ", "10/10/2018 14:10:23", "10/10/2018 20:10:23", "RT0467"));
            productlists.add(new Requerimiento("R008QW ", "10/10/2018 15:10:23", "10/10/2018 20:10:23", "RT0467"));
            productlists.add(new Requerimiento("R009QW ", "10/10/2018 16:10:23", "10/10/2018 20:10:23", "RT0467"));
            productlists.add(new Requerimiento("R010QW ", "10/10/2018 17:10:23", "10/10/2018 20:10:23", "RT0467"));
            productlists.add(new Requerimiento("R011QW ", "10/10/2018 18:10:23", "10/10/2018 20:10:23", "RT0467"));
            productlists.add(new Requerimiento("R012QW ", "10/10/2018 19:10:23", "10/10/2018 20:10:23", "RT0467"));
        }

        listarequerimientos = view.findViewById(R.id.listaReq);
        listarequerimientos.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listarequerimientos.setLayoutManager(linearLayoutManager);

        adapter = RequerimientoViewAdapter.newInstance().config()
                .setFragment(this)
                .setProductlistAdap(productlists)
                .setListener(this::clickCard)
                .build();

        listarequerimientos.setAdapter(adapter);
    }


    private void clickCard(Requerimiento requerimiento) {
        guardarListaProductos();
        //this.setTipoAccion(DETALLE_REQUERIMIENTO);
        onButtonPressed(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_requerimiento, menu);
    }

    public RequerimientoFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public RequerimientoFragment setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
    }

    private void guardarListaProductos() {
        SharedPreferenceProductos.getInstance().setActivity(getActivity()).guardar(
                new ArrayList<Producto>() {{
                    add(new Producto().config().setCodigo(1).setNombre("Keke").setDescripcion("sabor chocolate con chispas").setCantidad(3)
                            .setPrecioUnitario(3.0).actualizarPrecioCantidad().build());
                    add(new Producto().config().setCodigo(2).setNombre("Pastel").setDescripcion("sabor de vainilla con manjar blanco").setCantidad(34)
                            .setPrecioUnitario(3.0).actualizarPrecioCantidad().build());
                    add(new Producto().config().setCodigo(3).setNombre("desinfectante").setDescripcion("Limpia los baños y el labado").setCantidad(3)
                            .setPrecioUnitario(3.0).actualizarPrecioCantidad().build());
                    add(new Producto().config().setCodigo(4).setNombre("Quita grasa").setDescripcion("Quita toda la grasa del los patos y las cosas").setCantidad(6)
                            .setPrecioUnitario(3.0).actualizarPrecioCantidad().build());
                    add(new Producto().config().setCodigo(5).setNombre("Arroz").setDescripcion("Arroz rompe olla para tu casa").setCantidad(1)
                            .setPrecioUnitario(3.0).actualizarPrecioCantidad().build());
                    add(new Producto().config().setCodigo(6).setNombre("Orix").setDescripcion("Orix, a la grasa le pone fin").setCantidad(4)
                            .setPrecioUnitario(3.0).actualizarPrecioCantidad().build());
                }}
        );

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Fragment faFragment);
    }

    public void onQueryTextSubmit(String query) {
        Toast.makeText(getActivity(), "User Query: " + query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryTextChange(String editable) {
        // textView.setText(editable);
    }

}

