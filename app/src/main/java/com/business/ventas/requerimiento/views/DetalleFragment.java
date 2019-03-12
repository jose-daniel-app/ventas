package com.business.ventas.requerimiento.views;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.beans.Requerimiento;
import com.business.ventas.requerimiento.contracts.DetalleContract;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Fechas;
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
    TextView txtCodigoRequerimiento;
    TextView txtTitulo;
    TextView txtFechaEmision;
    TextView txtFechaEntrega;

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
        log.info(this.requerimiento.toString());
        this.presenter.solicitarDetalleRequerimiento(this.requerimiento);
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
        txtCodigoRequerimiento = view.findViewById(R.id.txtCodigoRequerimiento);
        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtFechaEmision = view.findViewById(R.id.txtFechaEmision);
        txtFechaEntrega = view.findViewById(R.id.txtFechaEntrega);

        listViewItem.setOnItemClickListener(this::clickItemListView);
    }

    private void clickItemListView(AdapterView<?> adapterView, View view, int i, long l) {
        DialogFullScreenProductos dialog = DialogFullScreenProductos.getBuilder()
                .setRequerimiento(this.requerimiento)
                .setProductoElegido(this.requerimiento.getItems().get(i))
                .setOnActualizarRequerimiento(rq -> {
                    mensajeToast("Se actualizo la orden %s", rq.getName());
                    this.presenter.solicitarDetalleRequerimiento(rq);
                    this.mostrarProgresBar(true);
                }).Build();

        dialog.show(getFragmentManager().beginTransaction(),
                DialogFullScreenProductos.class.getSimpleName());
    }

    public static DetalleFragment newInstance() {
        return new DetalleFragment();
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }

    @Override
    public void mostrarRequerimiento(Requerimiento requerimiento) {

        txtCodigoRequerimiento.setText(requerimiento.getName());
        txtTitulo.setText(requerimiento.getTitle());
        txtFechaEmision.setText(Fechas.darFormatoALaFecha("dd/MM/yyyy",requerimiento.getTransactionDate()));
        txtFechaEntrega.setText(Fechas.darFormatoALaFecha("dd/MM/yyyy",requerimiento.getScheduleDate()));

        adapter = new ItemPedidosBaseAdapter(getActivity(), R.layout.view_item_pedido, requerimiento.getItems());
        listViewItem.setAdapter(adapter);
        this.mostrarProgresBar(false);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        mensajeToast(mensaje);
        log.info(mensaje);
        this.mostrarProgresBar(false);
    }
}
