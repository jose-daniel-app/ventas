package com.business.ventas.ordenes.views;

import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Cliente;
import com.business.ventas.beans.Orden;
import com.business.ventas.beans.Producto;
import com.business.ventas.search.SearchToolbarProducto;
import com.business.ventas.search.SearchToolbarProducto.OnSearchToolbarQueryTextListner;
import com.business.ventas.ordenes.contracts.ProductosContract;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Fechas;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ProductoViewAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.Date;
import java.util.List;


public class ProductosFragment extends AppFragment implements OnSearchToolbarQueryTextListner, ProductosContract.View {

    VentasLog log = LogFactory.createInstance().setTag(ProductosFragment.class.getSimpleName());

    RecyclerView recyclerView;
    ProductoViewAdapter adapter;

    SearchToolbarProducto searchToolbar;
    ProductosContract.Presenter presenter;

    FloatingActionButton floatingActionButton;
    FloatingActionMenu fabMenu;
    ProgressBar progressBar;
    private List<Producto> productos;

    // parametro.
    private Cliente cliente;

    public ProductosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        presenter = ProductosContract.createInstance(ProductosContract.Presenter.class)
                .setContext(getMainActivity())
                .setView(this);

        View view = inflater.inflate(R.layout.fragment_productos, container, false);
        loadComponents(view);
        toolbar.setTitle(R.string.title_producto);
        navigationView.setCheckedItem(R.id.nav_ordenes);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.productos_menu);
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        searchToolbar = new SearchToolbarProducto(getActivity(), this, getActivity().findViewById(R.id.search_producto));
        mostrarProgresBar(true);
        presenter.solicitarProductos(cliente.getDireccion());
        return view;
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.ic_search:
                searchToolbar.openSearchToolbar();
                break;
        }
        return true;
    }

    private void loadComponents(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.listaPro);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mGridLayoutManager);
        floatingActionButton = view.findViewById(R.id.menu_item);
        floatingActionButton.setOnClickListener(this::clickItemButon);

        fabMenu = view.findViewById(R.id.floatingActionButonContinuar);
        fabMenu.setIconAnimated(false);
    }

    private void clickItemButon(View view) {
        Lista<Producto> productos = adapter.obtenerProductosElegidos();

        if (productos.size() > 0) {
            Orden orden = new Orden();
            orden.setNombreCliente(cliente.getNombre());
            orden.setTerritorio(cliente.getDireccion());
            orden.setFechaEntrega(new Date());
            orden.setProductos(productos);
            presenter.crearNuevaOrden(orden);
            mostrarProgresBar(true);
            //searchToolbar.closeSearchToolbar();
        } else {
            mensajeToast("No se a seleccionado ningun producto.");
        }
    }

    public static ProductosFragment newInstance() {
        return new ProductosFragment();
    }


    public void onQueryTextSubmit(String query) {
        Toast.makeText(getActivity(), "User Query: " + query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryTextChange(String editable) {
        // textView.setText(editable);
        adapter.setfilter(new Lista<>(productos)
                .filtrar(producto -> producto.getNombre().toLowerCase().startsWith(editable.toLowerCase())));
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        mensajeToast(mensaje);
        log.info(mensaje);
        mostrarProgresBar(false);
    }

    @Override
    public void cargarProductos(List<Producto> productos) {
        adapter = ProductoViewAdapter.newInstance().config()
                .setActivity(getActivity())
                .setProductlistAdap(new Lista<>(productos))
                .build();
        recyclerView.setAdapter(adapter);
        this.productos = productos;
        mostrarProgresBar(false);
    }

    public ProductosFragment setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    @Override
    public void respuestaCrearOrden(Orden orden) {
        mensajeToast("se creo la orden "+orden.getCodigo());
        mostrarProgresBar(false);
        getMainActivity().newFragmentHandler().changeFragment(OrdenFragment.newInstance(orden.getCodigo()));
    }
}
