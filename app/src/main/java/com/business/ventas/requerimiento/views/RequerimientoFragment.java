package com.business.ventas.requerimiento.views;

import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.business.ventas.R;
import com.business.ventas.beans.Requerimiento;
import com.business.ventas.requerimiento.contracts.RequerimientoContract;
import com.business.ventas.search.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.RequerimientoViewAdapter;

import java.util.List;


public class RequerimientoFragment extends AppFragment implements OnSearchToolbarQueryTextListner, RequerimientoContract.View {

    VentasLog log = LogFactory.createInstance().setTag(RequerimientoFragment.class.getSimpleName());

    RecyclerView listarequerimientos;
    RequerimientoViewAdapter adapter;

    FloatingActionButton floatingActionButtonAgregar;
    ProgressBar progressBar;
    RequerimientoContract.Presenter presenter;

    public RequerimientoFragment() {
    }

    public static RequerimientoFragment newInstance() {
        return new RequerimientoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requerimiento, container, false);
        loadComponents(view);
        toolbar.setTitle(R.string.title_requerimiento);
        navigationView.setCheckedItem(R.id.nav_requerimiento);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_requerimiento);
     //   toolbar.setOverflowIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_date_range));
        presenter = RequerimientoContract.newPresenter().setContext(getContext()).setView(this);
        presenter.solicitarRequerimientos();
        mostrarProgresBar(true);
        return view;
    }

    private void loadComponents(View view) {

        floatingActionButtonAgregar = view.findViewById(R.id.fbAgregarRuta);
        floatingActionButtonAgregar.setOnClickListener(this::btnCrearRequerimiento);
        progressBar = view.findViewById(R.id.progressBar);

        listarequerimientos = view.findViewById(R.id.listaReq);
        listarequerimientos.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listarequerimientos.setLayoutManager(linearLayoutManager);

    }

    private void clickCard(Requerimiento requerimiento) {
        getMainActivity().newFragmentHandler().changeFragment(DetalleFragment.newInstance().setRequerimiento(requerimiento));
    }

    private void btnCrearRequerimiento(View view) {
        getMainActivity().newFragmentHandler().changeFragment(RutaFragment.newInstance());
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_requerimiento, menu);
    }

    public void onQueryTextSubmit(String query) {
        Toast.makeText(getActivity(), "User Query: " + query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryTextChange(String editable) {
    }

    @Override
    public void mostrarLosRequerimientos(List<Requerimiento> requerimientos) {
        adapter = RequerimientoViewAdapter.newInstance().config()
                .setFragment(this)
                .setProductlistAdap(requerimientos)
                .setListener(this::clickCard)
                .build();

        listarequerimientos.setAdapter(adapter);
        mostrarProgresBar(false);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        log.info(mensaje);
        mostrarProgresBar(false);
    }
}

