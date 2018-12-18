package com.business.ventas.requerimiento.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.requerimiento.contracts.ReqProductoContract;
import com.business.ventas.search.SearchToolbarProducto;
import com.business.ventas.search.SearchToolbarProducto.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ReqProductoViewAdapter;
import java.util.List;


public class ReqProductoFragment extends AppFragment implements
        OnSearchToolbarQueryTextListner, ReqProductoContract.View {

    VentasLog log = LogFactory.createInstance().setTag(ReqProductoFragment.class.getSimpleName());

    RecyclerView recyclerView;
    ReqProductoViewAdapter adapter;
    SearchToolbarProducto searchToolbar;
    FloatingActionButton floatingActionButonContinuar;
    ProgressBar progressBar;

    ReqProductoContract.Presenter presenter;

    public ReqProductoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reqproducto, container, false);

        presenter = ReqProductoContract.createInstance(ReqProductoContract.Presenter.class)
                .setView(this).setContext(getMainActivity());

        loadComponents(view);
        toolbar.setTitle(R.string.title_producto);
        navigationView.setCheckedItem(R.id.nav_requerimiento);
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
        floatingActionButonContinuar = view.findViewById(R.id.floatingActionButonContinuar);
        floatingActionButonContinuar.setOnClickListener(this::clickBtnContinuar);

    }

    private void clickBtnContinuar(View view) {
        /*new MaterialDialog.Builder(getActivity())
                .title(R.string.mensaje_compra)
                .positiveText(R.string.mensaje_ok).onPositive((dialog, which)->{ onButtonPressed(this); })
                .negativeText(R.string.mensaje_cancelar)
                .show();*/
    }

    public static ReqProductoFragment newInstance() {
        return new ReqProductoFragment();
    }

    public ReqProductoFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public ReqProductoFragment setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
    }

    @Override
    public void onQueryTextSubmit(String query) {

    }

    @Override
    public void onQueryTextChange(String editable) {

    }

    public void mostrarProgresBar(Boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }

    @Override
    public void cargarProductos(List<Producto> productos) {
        log.info("la cantidad es: %d", productos.size());
        adapter = new ReqProductoViewAdapter().config()
            .setActivity(getActivity())
            .setProductlistAdap(productos)
            .build();
        recyclerView.setAdapter(adapter);
        mostrarProgresBar(false);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        log.info(mensaje);
        mostrarProgresBar(false);
    }
}
