package com.business.ventas.requerimiento.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.business.ventas.beans.Requerimiento;
import com.business.ventas.requerimiento.contracts.DialogProductosContract;
import com.business.ventas.search.SearchToolbarProducto;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ProductoViewAdapter;

public class DialogFullScreenProductos extends DialogFragment implements SearchToolbarProducto.OnSearchToolbarQueryTextListner, DialogProductosContract.View {

    VentasLog log = LogFactory.createInstance().setTag(DialogFullScreenProductos.class.getSimpleName());

    private Lista<Producto> productos;
    private Producto productoElegido;
    private DialogProductosContract.Presenter presenter;
    private ProgressBar progressBar;
    private FrameLayout frameLayout;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ProductoViewAdapter adapter;
    private SearchToolbarProducto searchToolbar;
    private Requerimiento requerimiento;
    private EventoActualizarRequerimiento evento;

    public DialogFullScreenProductos() {
    }

    public static DialogProductosBuilder getBuilder() {
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
        this.requerimiento.setItems(this.productos.filtrar(p -> p.getCantidad() > 0));
        this.presenter.solicitarActualizarRequerimiento(this.requerimiento);
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

    private RecyclerView.LayoutManager getTipoDedisenio() {
        //RecyclerView.SmoothScroller smoothScroller = new Grid
        return new GridLayoutManager(getActivity(), 2);
    }

    private void solicitarLosProductos(DialogProductosContract.Presenter presenter) {
        presenter.solicitarProductos();
        this.mostrarProgresBar(true);
    }

    private DialogProductosContract.Presenter instanciarPresenter() {
        return DialogProductosContract.newPresenter()
                .setContext(getContext())
                .setView(this);
    }

    private ProductoViewAdapter instanciarProductoViewAdapter(Lista<Producto> productos, int posicion) {
        return ProductoViewAdapter.newInstance().config()
                .setActivity(getActivity())
                .setFocusPosicion(posicion)
                .setProductlistAdap(productos)
                .setEventoProductoAgregado(p1 -> {
                    this.productos.foreach(p2 -> {
                        if (p1.getItemCode().equals(p2.getItemCode())) {
                            p2.setCantidad(p1.getCantidad());
                            p2.actualizarPrecioCantidad();
                        }
                    });
                }).build();
    }

    private Lista<Producto> cambiarLasCantidadesSegunOrden(Requerimiento requerimiento, Lista<Producto> productos) {
        return productos.foreach(p1 -> {
            new Lista<>(requerimiento.getItems()).foreach(p2 -> {
                if (p1.getItemCode().equals(p2.getItemCode())) p1.setCantidad(p2.getCantidad());
            });
        });
    }

    public Requerimiento getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(Requerimiento requerimiento) {
        this.requerimiento = requerimiento;
    }

    public void setEvento(EventoActualizarRequerimiento evento) {
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
        int posicion = this.obtenerPosicion(this.productoElegido);
        this.adapter = this.instanciarProductoViewAdapter(cambiarLasCantidadesSegunOrden(this.requerimiento, this.productos), posicion);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.scrollToPosition(posicion);
        this.mostrarProgresBar(false);
    }

    private int obtenerPosicion(Producto p) {
        for (int i = 0; i < this.productos.size(); i++) {
            Producto producto = this.productos.get(i);
            if (producto.getItemCode().equals(p.getItemCode())) {
                return i;
            }
        }
        return 0;
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
    public void respuestaActualizarRequerimiento(Requerimiento requerimiento) {
        this.mostrarProgresBar(false);
        this.evento.onRequerimientoUpdate(requerimiento);
        log.info("se actualizo la orden");
        this.getDialog().cancel();
    }

    @FunctionalInterface
    public interface EventoActualizarRequerimiento {
        void onRequerimientoUpdate(Requerimiento requerimiento);
    }

    public interface DialogProductosBuilder {
        DialogProductosBuilder setRequerimiento(Requerimiento requerimiento);

        DialogProductosBuilder setProductoElegido(Producto productoElegido);

        DialogProductosBuilder setOnActualizarRequerimiento(EventoActualizarRequerimiento evento);

        DialogFullScreenProductos Build();
    }

    private static class DialogProductosBuilderImpl implements DialogProductosBuilder {

        private DialogFullScreenProductos dfsp;

        public DialogProductosBuilderImpl(DialogFullScreenProductos dfsp) {
            this.dfsp = dfsp;
        }

        @Override
        public DialogProductosBuilder setOnActualizarRequerimiento(EventoActualizarRequerimiento evento) {
            this.dfsp.evento = evento;
            return this;
        }

        @Override
        public DialogProductosBuilder setRequerimiento(Requerimiento requerimiento) {
            this.dfsp.requerimiento = requerimiento;
            return this;
        }

        @Override
        public DialogProductosBuilder setProductoElegido(Producto productoElegido) {
            this.dfsp.productoElegido = productoElegido;
            return this;
        }

        @Override
        public DialogFullScreenProductos Build() {
            return this.dfsp;
        }
    }

}
