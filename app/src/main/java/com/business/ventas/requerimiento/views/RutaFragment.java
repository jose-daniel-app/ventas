package com.business.ventas.requerimiento.views;


import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Requerimiento;
import com.business.ventas.beans.Ruta;
import com.business.ventas.requerimiento.contracts.RutaContract;
import com.business.ventas.search.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Fechas;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.RutaViewAdapter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RutaFragment extends AppFragment implements OnSearchToolbarQueryTextListner, RutaContract.View {

    VentasLog log = LogFactory.createInstance().setTag(RutaFragment.class.getSimpleName());
    RutaContract.Presenter presenter;

    RecyclerView recyclerViewRutas;
    RutaViewAdapter adapter;
    ProgressBar progressBar;

    public RutaFragment() {
    }

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

        presenter = RutaContract.createInstance(RutaContract.Presenter.class).setContext(getMainActivity()).setView(this);
        presenter.listarRutas();
        mostrarProgresBar(true);
        return view;
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }

    private void loadComponents(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        recyclerViewRutas = view.findViewById(R.id.listaRuta);
        recyclerViewRutas.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewRutas.setLayoutManager(linearLayoutManager);
    }

    private void clickCardViewRuta(Ruta ruta) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance().configEvent(fecha -> {
            Requerimiento requerimiento = new Requerimiento();
            requerimiento.setTransactionDate(new Date());
            requerimiento.setScheduleDate(fecha);
            requerimiento.setRuta(ruta);
            getMainActivity().newFragmentHandler()
                    .changeFragment(ReqProductoFragment.newInstance().setRequerimiento(requerimiento));
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

    @Override
    public void mostrarRutas(List<Ruta> rutas) {
        adapter = RutaViewAdapter.newInstance().config()
                .setFragment(this).setListaRuta(rutas)
                .setListenerItem(this::clickCardViewRuta)
                .build();
        recyclerViewRutas.setAdapter(adapter);
        mostrarProgresBar(false);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        log.error(mensaje);
        mostrarProgresBar(false);
    }
}

