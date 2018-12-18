package com.business.ventas.comprobante.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Comprobante;
import com.business.ventas.beans.Producto;
import com.business.ventas.comprobante.contracts.DetalleGuiaContract;
import com.business.ventas.login.views.MainActivity;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.viewAdapter.ItemPedidosBaseAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class DetalleGuiaFragment extends AppFragment implements DetalleGuiaContract.View {

    DetalleGuiaContract.Presenter presenter;

    ListView listViewItem;
    ItemPedidosBaseAdapter adapter;
    FloatingActionMenu fabMenu;
    FloatingActionButton item1;
    FloatingActionButton item2;
    FloatingActionButton item3;

    ProgressBar progressBar;
    LinearLayout linearLayoutMain;
    TextView txtNombreCliente;
    TextView txtTotal;

    private String codigo;
    private int tipoDocumento;


    public DetalleGuiaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_guia, container, false);
        loadComponents(view);

        presenter = DetalleGuiaContract.createInstance(DetalleGuiaContract.Presenter.class).setContext(getMainActivity()).setView(this);
        if(codigo != null && tipoDocumento != 0){
            mostrarProgresBar(true);
            presenter.pedirDetalleComprobante(codigo,tipoDocumento);
        }
        return view;
    }

    private void loadComponents(View view) {
        toolbar.setTitle(R.string.title_detalle_guia);
        navigationView.setCheckedItem(R.id.nav_ordenes);
        toolbar.getMenu().clear();
        listViewItem = view.findViewById(R.id.listViewItem);
        progressBar = view.findViewById(R.id.progressBar);
        txtNombreCliente = view.findViewById(R.id.txtNombreCliente);
        txtTotal = view.findViewById(R.id.txtTotal);
        linearLayoutMain = view.findViewById(R.id.linearLayoutMain);
        fabMenu = view.findViewById(R.id.floatingActionButonContinuar);
        fabMenu.setIconAnimated(false);

        item1 = view.findViewById(R.id.menu_item1);
        item2 = view.findViewById(R.id.menu_item2);
        item3 = view.findViewById(R.id.menu_item3);

        item1.setOnClickListener(this::onClikItenMenu);
        item2.setOnClickListener(this::onClikItenMenu);
        item3.setOnClickListener(this::onClikItenMenu);
    }

    private void onClikItenMenu(View view) {
        int id = view.getId();
        MainActivity.FragmentHandler fh = getMainActivity().newFragmentHandler();
        switch (id){
            case R.id.menu_item1:
                fh.changeFragment(DocumentoPendienteFragment.newInstance().setTitulo("Factura"));
                break;
            case R.id.menu_item2:
                fh.changeFragment(DocumentoPendienteFragment.newInstance().setTitulo("Boleta"));
                break;
            case R.id.menu_item3:
                fh.changeFragment(DocumentoPendienteFragment.newInstance().setTitulo("Nota de ventas"));
                break;
        }
    }

    public static DetalleGuiaFragment newInstance() {
        return new DetalleGuiaFragment();
    }

    public static DetalleGuiaFragment newInstance(String codigo, int tipoDocumento) {
        DetalleGuiaFragment ordenFragment = new DetalleGuiaFragment();
        Bundle args = new Bundle();
        args.putString("codigo", codigo);
        args.putInt("tipoDocumento",tipoDocumento);
        ordenFragment.setArguments(args);
        return ordenFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.codigo = getArguments().getString("codigo");
            this.tipoDocumento = getArguments().getInt("tipoDocumento",0);
        }
    }

    private Lista<Producto> listaProducos() {
        return new Lista<Producto>(SharedPreferenceProductos.getInstance().setActivity(getActivity()).listarProducto())
                .filtar(p -> p.getCantidad() > 0);
    }

    @Override
    public void mostrarDetalleComprobante(Comprobante comprobante) {
        txtNombreCliente.setText(comprobante.getNombre());
        txtTotal.setText("S/ "+ comprobante.getPagoTotal());
        adapter = new ItemPedidosBaseAdapter(getActivity(), R.layout.view_item_pedido, comprobante.getProductos());
        listViewItem.setAdapter(adapter);
        mostrarProgresBar(false);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        mostrarProgresBar(false);
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
        linearLayoutMain.setVisibility(estado ? View.GONE : View.VISIBLE);
    }
}
