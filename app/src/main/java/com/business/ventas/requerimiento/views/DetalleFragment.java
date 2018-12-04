package com.business.ventas.requerimiento.views;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Lista;
import com.business.ventas.viewAdapter.ItemPedidosBaseAdapter;

import java.util.List;


public class DetalleFragment extends AppFragment {

    ListView listViewItem;
    ItemPedidosBaseAdapter adapter;

    public DetalleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        toolbar.setTitle(R.string.title_detalle);
        navigationView.setCheckedItem(R.id.nav_ordenes);
        toolbar.getMenu().clear();

        loadComponents(view);

        adapter = new ItemPedidosBaseAdapter(getActivity(), R.layout.view_item_pedido, listaProducos());
        listViewItem.setAdapter(adapter);
        return view;
    }

    private List<Producto> listaProducos() {
        return new Lista<Producto>()
            .agregar(new Producto().config()
                .setNombre("Bizcocho especial x12")
                .setCantidad(3).setPrecioUnitario(5.0)
                .actualizarPrecioCantidad()
                .build())
            .agregar(new Producto().config()
                .setNombre("Keke ingles x12")
                .setCantidad(2).setPrecioUnitario(2.0)
                .actualizarPrecioCantidad()
                .build())
            .agregar(new Producto().config()
                .setNombre("Empanada x20")
                .setCantidad(6).setPrecioUnitario(5.0)
                .actualizarPrecioCantidad()
                .build());
    }

    private void loadComponents(View view) {
        listViewItem = view.findViewById(R.id.listViewItem);
    }

    public static DetalleFragment newInstance() {
        return new DetalleFragment();
    }

}
