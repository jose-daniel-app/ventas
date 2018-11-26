package com.business.ventas.ordenes.views;

import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.login.views.SearchToolbarProducto;
import com.business.ventas.login.views.SearchToolbarProducto.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ProductoViewAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


public class ProductosFragment extends AppFragment implements OnSearchToolbarQueryTextListner {

    VentasLog log = LogFactory.createInstance().setTag(ProductosFragment.class.getSimpleName());

    RecyclerView recyclerView;
    ProductoViewAdapter adapter;

    SearchToolbarProducto searchToolbar;
    private SharedPreferenceProductos sharedProductos;

    FloatingActionButton floatingActionButton;
    FloatingActionMenu fabMenu;

    public ProductosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productos, container, false);
        sharedProductos = SharedPreferenceProductos.getInstance().setActivity(getActivity());
        loadComponents(view);
        toolbar.setTitle(R.string.title_producto);
        navigationView.setCheckedItem(R.id.nav_ordenes);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.productos_menu);
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        searchToolbar = new SearchToolbarProducto(getActivity(), this, getActivity().findViewById(R.id.search_producto));
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
        recyclerView = view.findViewById(R.id.listaPro);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mGridLayoutManager);
        floatingActionButton = view.findViewById(R.id.menu_item);
        floatingActionButton.setOnClickListener(this::clickItemButon);

        fabMenu = view.findViewById(R.id.floatingActionButonContinuar);
        fabMenu.setIconAnimated(false);

        adapter = ProductoViewAdapter.newInstance().config()
                .setActivity(getActivity())
                .setProductlistAdap(this.newListaDeProductos())
                .build();
        recyclerView.setAdapter(adapter);
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

    private Lista<Producto> newListaDeProductos() {
        return new Lista<Producto>()
            .agregar(new Producto(1, R.drawable.queque, "Keke x10", "sabor chocolate con chispas", 1.0))
            .agregar(new Producto(2, R.drawable.pastel, "Pastel x5", "sabor de vainilla con manjar blanco", 3.0))
            .agregar(new Producto(3, R.drawable.bizcocho, "Bizcocho x30", "Pan muy suabe", 10.0))
            .agregar(new Producto(4, R.drawable.bombas, "Bombas x20", "Rico pastel con manjar", 23.5))
            .agregar(new Producto(5, R.drawable.cachitos, "Cachito x10", "Pan en forma de cachito", 10.0))
            .agregar(new Producto(6, R.drawable.champa, "Champa x20", "rico keke con tres sabores", 3.0))
            .agregar(new Producto(7, R.drawable.cocadas, "Cocadas x10", "ricas cocadas crocantes", 3.0))
            .agregar(new Producto(8, R.drawable.donuts, "Donuts x15", "Ricas donuts con manjar", 3.0))
            .agregar(new Producto(9, R.drawable.kingkong, "Kingkong x20", "Kingkong con manjar blanco", 10.0))
            .agregar(new Producto(10, R.drawable.lengua, "Lengua x15", "Rico pastel manjar blanco", 13.0))
            .agregar(new Producto(11, R.drawable.milhojas, "Milhojas x25", "Rico paltes con chispas blascas", 3.0))
            .agregar(new Producto(12, R.drawable.orejas, "Orejas", "Ojejas con azucar", 3.0))
            .agregar(new Producto(13, R.drawable.panblancomolde, "Pan molde x50", "sabor chocolate con chispas", 3.0))
            .agregar(new Producto(14, R.drawable.roscas, "Roscas x25", "Roscas ricas con mantequilla", 3.0))
            .agregar(new Producto(15, R.drawable.suspiros, "Suspiros x25", "rico dulce de varios sabores", 3.0))
            .agregar(new Producto(16, R.drawable.turrones, "Turrones x5", "Rico turros con miel", 3.0))
            .filtar(p -> {
                for (Producto producto : sharedProductos.listarProducto()) {
                    if (p.getCodigo() == producto.getCodigo()) {
                        p.setCantidad(producto.getCantidad());
                        p.actualizarPrecioCantidad();
                    }
                }
                return true;
            });
    }

}
