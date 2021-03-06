package com.business.ventas.comprobante.views;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ItemBoletaViewAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class DocumentoCompletadoFragment extends AppFragment {

    VentasLog log = LogFactory.createInstance().setTag(DocumentoCompletadoFragment.class.getSimpleName());

    RecyclerView recyclerViewITemBoleta;
    ItemBoletaViewAdapter adapter;

    FloatingActionMenu fabMenu;
    FloatingActionButton item1;

    private SharedPreferenceProductos sharedProductos;
    private String titulo;

    public DocumentoCompletadoFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documento_completado, container, false);
        toolbar.setTitle((this.titulo == null) ? "Boleta" : this.titulo);
        navigationView.setCheckedItem(R.id.nav_ordenes);
        toolbar.getMenu().clear();
        sharedProductos = SharedPreferenceProductos.getInstance().setActivity(getActivity());
        loadComponents(view);
        return view;
    }

    private void loadComponents(View view) {

        fabMenu = view.findViewById(R.id.floatingActionButonContinuar);
        fabMenu.setIconAnimated(false);

        item1 = view.findViewById(R.id.menu_item1);
        item1.setOnClickListener(this::onClikItenMenu);


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

    public static DocumentoCompletadoFragment newInstance() {
        return new DocumentoCompletadoFragment();
    }

    public DocumentoCompletadoFragment setTitulo(String titulo) {
        this.titulo = titulo;
        //toolbar.setTitle(titulo);
        return this;
    }

    private Lista<Producto> listaProducto() {
        return new Lista<Producto>(sharedProductos.listarProducto()).filtrar(p -> p.getCantidad() > 0);
    }

}
