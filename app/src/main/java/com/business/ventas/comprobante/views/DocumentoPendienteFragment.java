package com.business.ventas.comprobante.views;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Comprobante;
import com.business.ventas.beans.Producto;
import com.business.ventas.comprobante.contracts.DocumentoPendienteContract;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ItemBoletaViewAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class DocumentoPendienteFragment extends AppFragment implements DocumentoPendienteContract.View {

    VentasLog log = LogFactory.createInstance().setTag(DocumentoPendienteFragment.class.getSimpleName());

    RecyclerView recyclerViewITemBoleta;
    ItemBoletaViewAdapter adapter;

    FloatingActionMenu fabMenu;
    FloatingActionButton item1;
    FloatingActionButton item2;

    LinearLayout linearLayoutMain;
    ProgressBar progressBar;
    TextView txtNameCliene;
    TextView txtTotal;

    private DocumentoPendienteContract.Presenter presenter;

    // variable para realizar el detalle..
    private Comprobante comprobante;
    // para cambiar el titulo del toolBar
    private String titulo;

    public DocumentoPendienteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documento_pendiente, container, false);
        toolbar.setTitle((this.titulo == null) ? "Boleta" : this.titulo);
        navigationView.setCheckedItem(R.id.nav_ordenes);
        toolbar.getMenu().clear();
        loadComponents(view);

        if (this.comprobante != null) {
            log.info("instanciando al presentador..");
            this.presenter = DocumentoPendienteContract
                    .createInstance(DocumentoPendienteContract.Presenter.class)
                    .setContext(getMainActivity()).setView(this);
            log.info("solicitando el detalle de la orden");
            mostrarProgresBar(true);
            presenter.pedirDetalleComprobante(comprobante.getCodigo(), comprobante.tipoDecomprobante());
        }

        return view;
    }

    private void loadComponents(View view) {

        fabMenu = view.findViewById(R.id.floatingActionButonContinuar);
        fabMenu.setIconAnimated(false);

        item1 = view.findViewById(R.id.menu_item1);
        item2 = view.findViewById(R.id.menu_item2);

        linearLayoutMain = view.findViewById(R.id.linearLayoutMain);
        progressBar = view.findViewById(R.id.progressBar);
        txtNameCliene = view.findViewById(R.id.txtNameCliene);
        txtTotal = view.findViewById(R.id.txtTotal);

        item1.setOnClickListener(this::onClikItenMenu);
        item2.setOnClickListener(this::onClikItenMenu);

        recyclerViewITemBoleta = view.findViewById(R.id.recyclerViewITemBoleta);
        recyclerViewITemBoleta.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewITemBoleta.setNestedScrollingEnabled(false);
    }

    private void onClikItenMenu(View view) {
        switch (view.getId()) {
            case R.id.menu_item1:
                getMainActivity().newFragmentHandler().changeFragment(DocumentoCompletadoFragment.newInstance().setTitulo("Pagado con Efectivo"));
                break;
            case R.id.menu_item2:
                getMainActivity().newFragmentHandler().changeFragment(DocumentoCompletadoFragment.newInstance().setTitulo("Pagado con Deposito"));
                break;
        }
    }

    public static DocumentoPendienteFragment newInstance() {
        return new DocumentoPendienteFragment();
    }

    public DocumentoPendienteFragment setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
        return this;
    }

    public DocumentoPendienteFragment setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
        linearLayoutMain.setVisibility(estado ? View.GONE : View.VISIBLE);
    }

    @Override
    public void mostrarDetalleComprobante(Comprobante comprobante) {
        log.info("mostrando el detalle del comprobante y agregando los productos");
        txtNameCliene.setText(comprobante.getNombre());
        txtTotal.setText("S/ " + comprobante.getPagoTotal());
        adapter = ItemBoletaViewAdapter.newInstance().config()
                .setActivity(getActivity())
                .setListaProductos(comprobante.getProductos())
                .build();
        recyclerViewITemBoleta.setAdapter(adapter);
        mostrarProgresBar(false);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        mostrarProgresBar(false);
        log.info(mensaje);
        mensajeToast(mensaje);
    }
}
