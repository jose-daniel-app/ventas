package com.business.ventas.comprobante.views;

import com.business.ventas.beans.Producto;
import com.business.ventas.login.views.SearchToolbar;

import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Comprobante;
import com.business.ventas.login.views.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.viewAdapter.ComprobanteViewAdapter;


import java.util.ArrayList;
import java.util.List;


public class ComprobanteFragment extends Fragment implements OnSearchToolbarQueryTextListner {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView listacomprobantes;
    List<Comprobante> productlists = new ArrayList<>();
    ComprobanteViewAdapter adapter;

    NavigationView navigationView;
    Toolbar toolbar;

    public ComprobanteFragment() {
        // Required empty public constructor
    }

    public static ComprobanteFragment newInstance(String param1, String param2) {
        ComprobanteFragment fragment = new ComprobanteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static ComprobanteFragment newInstance() {
        return new ComprobanteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        onButtonPressed(this);
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        return true;
    }


    // TODO: Renombrar método, actualizar argumento y enganchar método en evento UI
    public void onButtonPressed(Fragment faFragment) {
        if (mListener != null) {
            mListener.onFragmentInteraction(faFragment);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public ComprobanteFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public void onQueryTextSubmit(String query) {
        Toast.makeText(getActivity(), "User Query: " + query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryTextChange(String editable) {
    }

    public ComprobanteFragment setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Fragment faFragment);
    }

}

