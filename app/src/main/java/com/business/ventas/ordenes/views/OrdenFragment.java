package com.business.ventas.ordenes.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.comprobante.views.DetalleGuiaFragment;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.viewAdapter.ItemPedidosBaseAdapter;
import com.github.clans.fab.FloatingActionButton;

public class OrdenFragment extends AppFragment {

    ListView listViewItem;
    ItemPedidosBaseAdapter adapter;
    FloatingActionButton item1;

    public OrdenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orden, container, false);
        loadComponents(view);
        return view;
    }

    private void loadComponents(View view) {
        toolbar.setTitle(R.string.title_orden);
        navigationView.setCheckedItem(R.id.nav_ordenes);
        toolbar.getMenu().clear();
        listViewItem = view.findViewById(R.id.listViewItem);
        item1 = view.findViewById(R.id.item1);
        item1.setOnClickListener(this::clickItem);
        adapter = new ItemPedidosBaseAdapter(getMainActivity(), R.layout.view_item_pedido, listaProducos());
        listViewItem.setAdapter(adapter);
    }

    private void clickItem(View view) {
        //TODO
       getMainActivity().newFragmentHandler().changeFragment(DetalleGuiaFragment.newInstance());
    }


    public static OrdenFragment newInstance() {
        return new OrdenFragment();
    }

    public OrdenFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public OrdenFragment setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
    }

    private Lista<Producto> listaProducos() {
        return new Lista<Producto>(SharedPreferenceProductos.getInstance().setActivity(getActivity()).listarProducto())
                .filtar(p -> p.getCantidad() > 0);
    }
}
