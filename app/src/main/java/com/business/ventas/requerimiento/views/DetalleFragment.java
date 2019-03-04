package com.business.ventas.requerimiento.views;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.beans.Requerimiento;
import com.business.ventas.requerimiento.contracts.DetalleContract;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ItemPedidosBaseAdapter;

import java.util.List;


public class DetalleFragment extends AppFragment implements DetalleContract.View {

    ListView listViewItem;
    ItemPedidosBaseAdapter adapter;
    DetalleContract.Presenter presenter;
    ProgressBar progressBar;
    Requerimiento requerimiento;

    VentasLog log = LogFactory.createInstance().setTag(DetalleFragment.class.getSimpleName());

    public DetalleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        toolbar.setTitle(R.string.title_detalle);
        navigationView.setCheckedItem(R.id.nav_ordenes);
        toolbar.getMenu().clear();

        loadComponents(view);
        this.presenter = DetalleContract.newPresenter().setContext(getContext()).setView(this);
        this.presenter.solicitarDetalleRequerimiento(requerimiento);
        this.mostrarProgresBar(true);
        return view;
    }

    public DetalleFragment setRequerimiento(Requerimiento requerimiento) {
        this.requerimiento = requerimiento;
        return this;
    }


    private void loadComponents(View view) {
        listViewItem = view.findViewById(R.id.listViewItem);
        progressBar = view.findViewById(R.id.progressBar);
    }

    public static DetalleFragment newInstance() {
        return new DetalleFragment();
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }

    @Override
    public void mostrarRequerimiento(Requerimiento requerimiento) {
        log.info("este es la cantidad d%",requerimiento.getItems().size());
        adapter = new ItemPedidosBaseAdapter(getActivity(), R.layout.view_item_pedido, requerimiento.getItems());
        listViewItem.setAdapter(adapter);
        this.mostrarProgresBar(false);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        log.info(mensaje);
        this.mostrarProgresBar(false);
    }
}
