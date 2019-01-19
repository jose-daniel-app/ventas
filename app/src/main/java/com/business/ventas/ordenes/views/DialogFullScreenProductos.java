package com.business.ventas.ordenes.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.business.ventas.R;
import com.business.ventas.beans.Orden;
import com.business.ventas.beans.Producto;
import com.business.ventas.ordenes.contracts.DialogProductosContract;
import com.business.ventas.search.SearchToolbarProducto;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ProductoViewAdapter;
import android.support.design.widget.FloatingActionButton;

public class DialogFullScreenProductos extends DialogFragment implements SearchToolbarProducto.OnSearchToolbarQueryTextListner, DialogProductosContract.View {

    VentasLog log = LogFactory.createInstance().setTag(DialogFullScreenProductos.class.getSimpleName());

    private Lista<Producto> productos;
    private DialogProductosContract.Presenter presenter;
    private ProgressBar progressBar;
    private FrameLayout frameLayout;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ProductoViewAdapter adapter;
    private SearchToolbarProducto searchToolbar;
    private Orden orden;
    private EventoActualizarOrden evento;

    public DialogFullScreenProductos() {
    }

    public static DialogProductosBuilder getBuilder(){
        return new DialogProductosBuilderImpl(new DialogFullScreenProductos());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_full_screen_productos, container, false);
        searchToolbar = new SearchToolbarProducto(getActivity(), this, view.findViewById(R.id.search_producto));

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        progressBar = view.findViewById(R.id.progressBar);
        frameLayout = view.findViewById(R.id.frameLayout);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(this::clickACtualizar);
        recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(this.getTipoDedisenio());

        toolbar.inflateMenu(R.menu.productos_menu);
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        toolbar.setNavigationOnClickListener(vista -> {
            this.getDialog().cancel();
        });

        toolbar.setOnMenuItemClickListener(this::clickBuscar);

        toolbar.setTitle(R.string.title_producto);

        this.presenter = this.instanciarPresenter();
        this.solicitarLosProductos(this.presenter);

        return view;
    }

    private void clickACtualizar(View view) {
        this.orden.setProductos(this.productos.filtrar(p -> p.getCantidad()>0));
        this.presenter.solicitarActualizarOrden(this.orden);
        this.mostrarProgresBar(true);
    }

    private boolean clickBuscar(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.ic_search:
                searchToolbar.openSearchToolbar();
                break;
        }
        return true;
    }

    private RecyclerView.LayoutManager getTipoDedisenio(){
        return new GridLayoutManager(getActivity(), 2);
    }

    private void solicitarLosProductos(DialogProductosContract.Presenter presenter) {
        presenter.solicitarProductos();
        this.mostrarProgresBar(true);
    }

    private DialogProductosContract.Presenter instanciarPresenter() {
        return DialogProductosContract.createInstance(DialogProductosContract.Presenter.class)
                .setContext(getContext())
                .setView(this);
    }

    private ProductoViewAdapter instanciarProductoViewAdapter(Lista<Producto> productos){
        return ProductoViewAdapter.newInstance().config()
            .setActivity(getActivity())
                .setProductlistAdap(productos)
                .setEventoProductoAgregado(p1->{
                    this.productos.foreach(p2 ->{
                        if(p1.getItemCode().equals(p2.getItemCode())) p2.setCantidad(p1.getCantidad());
                    });
                }).build();
    }

    private Lista<Producto> cambiarLasCantidadesSegunOrden(Orden orden, Lista<Producto> productos){
        return productos.foreach(p1 -> {
           orden.getProductos().foreach(p2 -> {
               if(p1.getItemCode().equals(p2.getItemCode())) p1.setCantidad(p2.getCantidad());
           });
        });
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public void setOnActualizarOrden(EventoActualizarOrden evento) {
        this.evento = evento;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
        frameLayout.setVisibility(estado ? View.GONE : View.VISIBLE);
    }

    @Override
    public void cargarProductos(Lista<Producto> productos) {
        this.productos = productos;
        this.adapter = this.instanciarProductoViewAdapter(cambiarLasCantidadesSegunOrden(this.orden, this.productos));
        this.recyclerView.setAdapter(adapter);
        this.mostrarProgresBar(false);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        log.error(mensaje);
        this.mostrarProgresBar(false);
    }

    @Override
    public void onQueryTextSubmit(String query) {

    }

    @Override
    public void onQueryTextChange(String editable) {

    }

    @Override
    public void respuestaActualizarOrden(Orden orden) {
        this.mostrarProgresBar(false);
        this.evento.onOrdenUpdate(orden);
        log.info("se actualizo la orden");
        this.getDialog().cancel();
    }

    @FunctionalInterface
    public interface EventoActualizarOrden {
        void onOrdenUpdate(Orden orden);
    }

    public interface DialogProductosBuilder {
        DialogProductosBuilder setOrden(Orden orden);
        DialogProductosBuilder setOnActualizarOrden(EventoActualizarOrden evento);
        DialogFullScreenProductos Build();
    }

    private static class DialogProductosBuilderImpl implements DialogProductosBuilder {

        private DialogFullScreenProductos dfsp;

        public DialogProductosBuilderImpl(DialogFullScreenProductos dfsp) {
            this.dfsp = dfsp;
        }

        @Override
        public DialogProductosBuilder setOnActualizarOrden(EventoActualizarOrden evento) {
            this.dfsp.evento = evento;
            return this;
        }

        @Override
        public DialogProductosBuilder setOrden(Orden orden) {
            this.dfsp.orden = orden;
            return this;
        }

        @Override
        public DialogFullScreenProductos Build() {
            return this.dfsp;
        }
    }

}
