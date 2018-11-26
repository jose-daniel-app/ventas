package com.business.ventas.comprobante.views;

import com.business.ventas.beans.Producto;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Comprobante;
import com.business.ventas.login.views.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.viewAdapter.ComprobanteViewAdapter;


import java.util.ArrayList;
import java.util.List;


public class ComprobanteFragment extends AppFragment implements OnSearchToolbarQueryTextListner {

    RecyclerView listacomprobantes;
    List<Comprobante> productlists = new ArrayList<>();
    ComprobanteViewAdapter adapter;

    public ComprobanteFragment() {
        // Required empty public constructor
    }

    public static ComprobanteFragment newInstance() {
        return new ComprobanteFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_comprobante, container, false);
        loadComponents(view);
        toolbar.setTitle(R.string.title_comprobante);
        navigationView.setCheckedItem(R.id.nav_vouchers);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_comprobante);
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_date_range));
        return view;
    }


    private void loadComponents(View view) {

        if (productlists.size() == 0) {
            productlists.add(new Comprobante("Ana Tarazona ", R.drawable.ic_account_circle_black_24dp, "104593895087", "10/10/2018 20:10:23", "3485786GBTX"));
            productlists.add(new Comprobante("Beto Silva ", R.drawable.ic_account_circle_black_24dp, "104593895087", "10/10/2018 20:10:23", "3485786GBTX"));
            productlists.add(new Comprobante("Carlos Alvarado ", R.drawable.ic_account_circle_black_24dp, "104593895087", "10/10/2018 20:10:23", "3485786GBTX"));
            productlists.add(new Comprobante("David Hernandez ", R.drawable.ic_account_circle_black_24dp, "104593895087", "10/10/2018 20:10:23", "3485786GBTX"));
            productlists.add(new Comprobante("Eduardo Vilca ", R.drawable.ic_account_circle_black_24dp, "104593895087", "10/10/2018 20:10:23", "3485786GBTX"));
            productlists.add(new Comprobante("Fernando Chavez", R.drawable.ic_account_circle_black_24dp, "104593895087", "10/10/2018 20:10:23", "3485786GBTX"));
            productlists.add(new Comprobante("Giovanni Pullido ", R.drawable.ic_account_circle_black_24dp, "104593895087", "10/10/2018 20:10:23", "3485786GBTX"));
            productlists.add(new Comprobante("Helena Garcia ", R.drawable.ic_account_circle_black_24dp, "104593895087", "10/10/2018 20:10:23", "3485786GBTX"));
            productlists.add(new Comprobante("Isabel Lloza", R.drawable.ic_account_circle_black_24dp, "104593895087", "10/10/2018 20:10:23", "3485786GBTX"));
            productlists.add(new Comprobante("Jose Sanchez", R.drawable.ic_account_circle_black_24dp, "104593895087", "10/10/2018 20:10:23", "3485786GBTX"));
        }

        listacomprobantes = view.findViewById(R.id.listaComp);
        listacomprobantes.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listacomprobantes.setLayoutManager(linearLayoutManager);

        adapter = ComprobanteViewAdapter.newInstance().config()
            .setFragment(this)
            .setProductlistAdap(productlists)
            .setOnSelectCardListener(this::onClickCard)
            .build();
        listacomprobantes.setAdapter(adapter);
    }

    private void onClickCard(Comprobante comprobante) {
        guardarListaProductos();
        //onButtonPressed(this);
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        return true;
    }

    public void onQueryTextSubmit(String query) {
        Toast.makeText(getActivity(), "User Query: " + query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryTextChange(String editable) {
    }

    private void guardarListaProductos(){
        SharedPreferenceProductos.getInstance().setActivity(getActivity()).guardar(
            new ArrayList<Producto>(){{
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

}

