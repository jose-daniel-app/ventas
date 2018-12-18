package com.business.ventas.comprobante.views;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Comprobante;
import com.business.ventas.beans.Factura;
import com.business.ventas.comprobante.contracts.ComprobanteContract;
import com.business.ventas.comprobante.contracts.DetalleGuiaContract;
import com.business.ventas.login.views.MainActivity;
import com.business.ventas.search.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ComprobanteViewAdapter;


import java.util.ArrayList;
import java.util.List;


public class ComprobanteFragment extends AppFragment implements OnSearchToolbarQueryTextListner, ComprobanteContract.View {

    VentasLog log = LogFactory.createInstance().setTag(ComprobanteFragment.class.getSimpleName());

    RecyclerView listacomprobantes;
    ComprobanteViewAdapter adapter;
    ProgressBar progressBar;


    private ComprobanteContract.Presenter presenter;

    public ComprobanteFragment() {

    }

    public static ComprobanteFragment newInstance() {
        return new ComprobanteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comprobante, container, false);
        loadComponents(view);
        toolbar.setTitle(R.string.title_comprobante);
        navigationView.setCheckedItem(R.id.nav_vouchers);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_comprobante);
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_date_range));

        presenter = ComprobanteContract.createInstance(ComprobanteContract.Presenter.class)
                .setContext(getMainActivity())
                .setView(this);
        mostrarProgresBar(true);
        log.info("solicitando comprobantes...");
        presenter.pedirComprobantes();
        return view;
    }

    private void loadComponents(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        listacomprobantes = view.findViewById(R.id.listaComp);
        listacomprobantes.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listacomprobantes.setLayoutManager(linearLayoutManager);
    }

    private void onClickCard(Comprobante comprobante) {
        MainActivity.FragmentHandler handlerFragment = getMainActivity().newFragmentHandler();
        if (comprobante.tipoDecomprobante() == Comprobante.FACTURA)
            handlerFragment.changeFragment(DocumentoPendienteFragment.newInstance().setTitulo("Factura"));
        else if (comprobante.tipoDecomprobante() == Comprobante.GUIA)
            handlerFragment.changeFragment(DetalleGuiaFragment.newInstance(comprobante.getCodigo(), comprobante.tipoDecomprobante()));

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

    @Override
    public void mostrarComprovantes(Lista<Comprobante> comprobantes) {
        comprobantes.foreach(p -> log.info(p.toString()));
        mostrarProgresBar(false);
        adapter = ComprobanteViewAdapter.newInstance().config()
                .setFragment(this)
                .setProductlistAdap(comprobantes)
                .setOnSelectCardListener(this::onClickCard)
                .build();
        listacomprobantes.setAdapter(adapter);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_LONG).show();
        mostrarProgresBar(false);
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }
}

