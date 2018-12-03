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
import com.business.ventas.beans.Producto;
import com.business.ventas.search.SearchToolbarProducto;
import com.business.ventas.search.SearchToolbarProducto.OnSearchToolbarQueryTextListner;
import com.business.ventas.ordenes.contracts.ProductosContracts;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ProductoViewAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;


public class ProductosFragment extends AppFragment implements OnSearchToolbarQueryTextListner, ProductosContracts.View {

    VentasLog log = LogFactory.createInstance().setTag(ProductosFragment.class.getSimpleName());

    RecyclerView recyclerView;
    ProductoViewAdapter adapter;

    SearchToolbarProducto searchToolbar;
    private SharedPreferenceProductos sharedProductos;
    ProductosContracts.Presenter presenter;

    FloatingActionButton floatingActionButton;
    FloatingActionMenu fabMenu;
    ProgressBar progressBar;

    public ProductosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        presenter = ProductosContracts.createInstance(ProductosContracts.Presenter.class)
                .SetContext(getMainActivity())
                .setView(this);

        View view = inflater.inflate(R.layout.fragment_productos, container, false);
        sharedProductos = SharedPreferenceProductos.getInstance().setActivity(getActivity());
        loadComponents(view);
        toolbar.setTitle(R.string.title_producto);
        navigationView.setCheckedItem(R.id.nav_ordenes);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.productos_menu);
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        searchToolbar = new SearchToolbarProducto(getActivity(), this, getActivity().findViewById(R.id.search_producto));
        mostrarProgresBar(true);
        presenter.solicitarProductos();
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
        sharedProductos.guardar(adapter.getProductlistAdap());
        getMainActivity().newFragmentHandler().changeFragment(OrdenFragment.newInstance());
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
    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        log.info(mensaje);
        mostrarProgresBar(false);
    }

    @Override
    public void cargarProductos(List<Producto> productos) {
        //new Lista<Producto>(productos).foreach(p -> log.info(p.toString()));
        adapter = ProductoViewAdapter.newInstance().config()
                .setActivity(getActivity())
                .setProductlistAdap(productos)
                .build();
        recyclerView.setAdapter(adapter);
        mostrarProgresBar(false);
    }
}
