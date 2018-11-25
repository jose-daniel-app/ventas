package com.business.ventas.ventas.views;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ItemBoletaViewAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BoletaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BoletaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoletaFragment extends Fragment {

    VentasLog log = LogFactory.createInstance().setTag(BoletaFragment.class.getSimpleName());
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    NavigationView navigationView;
    Toolbar toolbar;

    RecyclerView recyclerViewITemBoleta;
    ItemBoletaViewAdapter adapter;

    FloatingActionMenu fabMenu;
    FloatingActionButton item1;
    FloatingActionButton item2;

    private SharedPreferenceProductos sharedProductos;
    private String titulo;
    public BoletaFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boleta, container, false);
        toolbar.setTitle((this.titulo == null) ? "Boleta" : this.titulo);
        navigationView.setCheckedItem(R.id.nav_ventas);
        toolbar.getMenu().clear();
        sharedProductos = SharedPreferenceProductos.getInstance().setActivity(getActivity());
        loadComponents(view);
        return view;
    }

    private void loadComponents(View view) {

        fabMenu = view.findViewById(R.id.floatingActionButonContinuar);
        fabMenu.setIconAnimated(false);

        item1 = view.findViewById(R.id.menu_item1);
        item2 = view.findViewById(R.id.menu_item2);

        item1.setOnClickListener(this::onClikItenMenu);
        item2.setOnClickListener(this::onClikItenMenu);

        recyclerViewITemBoleta = view.findViewById(R.id.recyclerViewITemBoleta);
        recyclerViewITemBoleta.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewITemBoleta.setNestedScrollingEnabled(false);

        adapter = ItemBoletaViewAdapter.newInstance().config()
                .setActivity(getActivity())
                .setListaProductos(this.listaProducto())
                .build();
        recyclerViewITemBoleta.setAdapter(adapter);
    }

    private void onClikItenMenu(View view) {


    }

    public static BoletaFragment newInstance(String param1, String param2) {
        BoletaFragment fragment = new BoletaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static BoletaFragment newInstance() {
        return new BoletaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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

    public BoletaFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public BoletaFragment setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
    }

    public BoletaFragment setTitulo(String titulo){
        this.titulo = titulo;
        toolbar.setTitle(titulo);
        return this;
    }

    //TODO
    private Lista<Producto> listaProducto() {
        return new Lista<Producto>(sharedProductos.listarProducto()).filtar(p -> p.getCantidad() > 0);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Fragment faFragment);
    }
}
