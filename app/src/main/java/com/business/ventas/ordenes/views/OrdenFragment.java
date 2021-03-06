package com.business.ventas.ordenes.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Orden;
import com.business.ventas.beans.Producto;
import com.business.ventas.comprobante.views.DetalleGuiaFragment;
import com.business.ventas.ordenes.contracts.OrdenContract;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.DialogoConfimacion;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ItemPedidosBaseAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class OrdenFragment extends AppFragment implements OrdenContract.View {

    VentasLog log = LogFactory.createInstance().setTag(OrdenFragment.class.getSimpleName());

    ListView listViewItem;
    ItemPedidosBaseAdapter adapter;
    FloatingActionMenu fabMenu;
    FloatingActionButton item1;
    TextView txtTotal;
    TextView txtNameCliene;
    ProgressBar progressBar;
    LinearLayout linearLayout;


    private OrdenContract.Presenter presenter;
    private String codigoDeOrden;
    private Orden orden;

    public OrdenFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orden, container, false);
        loadComponents(view);

        this.presenter = OrdenContract.createInstance(OrdenContract.Presenter.class).setContext(getMainActivity()).setView(this);
        log.info("on create view verificando si el codigo es null: %s", codigoDeOrden);
        if (codigoDeOrden != null) {
            mostrarProgresBar(true);
            presenter.solicitarDetalleOrden(codigoDeOrden);
        }
        return view;
    }

    private void loadComponents(View view) {
        toolbar.setTitle(R.string.title_orden);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_orden);
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        navigationView.setCheckedItem(R.id.nav_ordenes);
        listViewItem = view.findViewById(R.id.listViewItem);
        listViewItem.setOnItemClickListener(this::clickItemListView);
        fabMenu = view.findViewById(R.id.floatingActionButonContinuar);
        fabMenu.setIconAnimated(false);
        item1 = view.findViewById(R.id.item1);
        progressBar = view.findViewById(R.id.progressBar);
        linearLayout = view.findViewById(R.id.linearLayout);
        txtNameCliene = view.findViewById(R.id.txtNameCliene);
        txtTotal = view.findViewById(R.id.txtTotal);
        item1.setOnClickListener(this::clickItem);
    }

    private void clickItemListView(AdapterView<?> adapterView, View view, int i, long l) {
        DialogFullScreenProductos dialog = DialogFullScreenProductos.getBuilder()
            .setOrden(this.orden)
            .setProductoElegido(this.orden.getProductos().get(i))
            .setOnActualizarOrden(orden -> {
                mensajeToast("Se actualizo la orden %s", orden.getCodigo());
                this.presenter.solicitarDetalleOrden(orden.getCodigo());
                this.mostrarProgresBar(true);
            }).Build();

        dialog.show(getFragmentManager().beginTransaction(), DialogFullScreenProductos.class.getSimpleName());
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item_check_boleta:
                mostrarMensajeConfirmacion();
                break;
            default:
        }
        return true;
    }

    private void mostrarMensajeConfirmacion() {
        new DialogoConfimacion(getFragmentManager())
                .setMensaje("¿Eliminar la orden?")
                .setDescripcionCancelar("NO")
                .setDescripcionConfirmar("SI")
                .setAccionConfirmar(() -> {
                    Orden ordenEli = new Orden();
                    ordenEli.setCodigo(codigoDeOrden);
                    presenter.solicitarEliminarOrden(ordenEli);
                    mostrarProgresBar(true);
                })
                .show();
    }

    private void clickItem(View view) {
        getMainActivity().newFragmentHandler().changeFragment(DetalleGuiaFragment.newInstance());
    }

    public static OrdenFragment newInstance() {
        return new OrdenFragment();
    }

    public static OrdenFragment newInstance(String codigo) {
        OrdenFragment ordenFragment = new OrdenFragment();
        Bundle args = new Bundle();
        args.putString("codigo", codigo);
        ordenFragment.setArguments(args);
        return ordenFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.info("verificando si existe argumentos.. ");
        if (getArguments() != null) {
            log.info("existe argumentos...");
            this.codigoDeOrden = getArguments().getString("codigo");
        }
    }

    public OrdenFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public OrdenFragment setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
        linearLayout.setVisibility(estado ? View.GONE : View.VISIBLE);
    }

    @Override
    public void mostrarDetalleOrden(Orden orden) {
        this.orden = orden;
        txtTotal.setText("s/ " + orden.getTotalGeneral());
        txtNameCliene.setText(orden.getNombreCliente());
        adapter = new ItemPedidosBaseAdapter(getMainActivity(), R.layout.view_item_pedido,
                orden.getProductos().ordenar((p1,p2) -> Integer.valueOf(p1.getCantidad()).compareTo(p2.getCantidad())));
        listViewItem.setAdapter(adapter);
        mostrarProgresBar(false);
    }

    @Override
    public void respuestaEliminarOrden(String mensaje) {
        mensajeToast("se elimino la orden " + codigoDeOrden);
        mostrarProgresBar(false);
        codigoDeOrden = null;
        getMainActivity().newFragmentHandler().changeFragment(OrdenesFragment.newInstance());
    }

    @Override
    public void errorRespuesta(String mensaje) {
        log.error(mensaje);
        mensajeToast(mensaje);
        mostrarProgresBar(false);
    }
}
