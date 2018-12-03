package com.business.ventas.requerimiento.views;


import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Ruta;
import com.business.ventas.search.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.RutaViewAdapter;


import java.util.ArrayList;
import java.util.List;

public class RutaFragment extends AppFragment implements OnSearchToolbarQueryTextListner {

    VentasLog log = LogFactory.createInstance().setTag(RutaFragment.class.getSimpleName());

    RecyclerView listarutas;
    List<Ruta> productlists = new ArrayList<>();
    RutaViewAdapter adapter;

    public RutaFragment() {}

    public static RutaFragment newInstance() {
        return new RutaFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ruta, container, false);
        loadComponents(view);
        toolbar.setTitle(R.string.title_ruta);
        navigationView.setCheckedItem(R.id.nav_requerimiento);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_ruta);
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        return view;

    }

    private void loadComponents(View view) {

        if (productlists.size() == 0) {
            productlists.add(new Ruta("SM00037F", "Urb. Los cedros de naranjal Mz B LT19", "Lima", "San Martin de Porres"));
            productlists.add(new Ruta("MF0038F", "An. Josè Pardo 1116", "Lima", "Miraflores"));
            productlists.add(new Ruta("BR0039F", "Jirón, Pichincha 485", "Lima", "Breña"));
            productlists.add(new Ruta("SM0040F", "Urb. Los cedros de naranjal Mz B LT19", "Lima", "San Martin de Porres"));

        }

        listarutas = view.findViewById(R.id.listaRuta);
        listarutas.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listarutas.setLayoutManager(linearLayoutManager);

        adapter = RutaViewAdapter.newInstance().config()
                .setFragment(this).setListaRuta(productlists)
                .setListenerItem(this::clickCardViewRuta)
                .build();

        listarutas.setAdapter(adapter);
    }

    private void clickCardViewRuta(String codigoRuta) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance().configEvent(fecha -> {
            log.info("La fecha es: " + fecha + " codigoRuta: " + codigoRuta);
            getMainActivity().newFragmentHandler().changeFragment(ReqProductoFragment.newInstance());
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }


    private boolean onMenuItemClick(MenuItem menuItem) {
        return true;
    }

    public void onQueryTextSubmit(String query) {
        Toast.makeText(getActivity(), "User Query: " + query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryTextChange(String editable) {
        // textView.setText(editable);
    }

}

