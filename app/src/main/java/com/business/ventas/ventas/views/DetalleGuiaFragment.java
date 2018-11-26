package com.business.ventas.ventas.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.login.views.MainActivity;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.viewAdapter.ItemPedidosBaseAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class DetalleGuiaFragment extends AppFragment {

    ListView listViewItem;
    ItemPedidosBaseAdapter adapter;
    FloatingActionMenu fabMenu;
    FloatingActionButton item1;
    FloatingActionButton item2;
    FloatingActionButton item3;


    public DetalleGuiaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_guia, container, false);
        loadComponents(view);
        return view;
    }

    private void loadComponents(View view) {
        toolbar.setTitle(R.string.title_detalle_guia);
        navigationView.setCheckedItem(R.id.nav_ventas);
        toolbar.getMenu().clear();
        listViewItem = view.findViewById(R.id.listViewItem);
        fabMenu = view.findViewById(R.id.floatingActionButonContinuar);
        fabMenu.setIconAnimated(false);

        item1 = view.findViewById(R.id.menu_item1);
        item2 = view.findViewById(R.id.menu_item2);
        item3 = view.findViewById(R.id.menu_item3);

        item1.setOnClickListener(this::onClikItenMenu);
        item2.setOnClickListener(this::onClikItenMenu);
        item3.setOnClickListener(this::onClikItenMenu);

        adapter = new ItemPedidosBaseAdapter(getActivity(), R.layout.view_item_pedido, listaProducos());
        listViewItem.setAdapter(adapter);
        // cagar los componentes del layout
    }

    private void onClikItenMenu(View view) {
        int id = view.getId();
        MainActivity.FragmentHandler fh = getMainActivity().newFragmentHandler();
        switch (id){
            case R.id.menu_item1:
                fh.changeFragment(BoletaFragment.newInstance().setTitulo("Factura"));
                break;
            case R.id.menu_item2:
                fh.changeFragment(BoletaFragment.newInstance().setTitulo("Boleta"));
                break;
            case R.id.menu_item3:
                fh.changeFragment(BoletaFragment.newInstance().setTitulo("Nota de ventas"));
                break;
        }
    }

    public static DetalleGuiaFragment newInstance() {
        return new DetalleGuiaFragment();
    }

    private Lista<Producto> listaProducos() {
        return new Lista<Producto>(SharedPreferenceProductos.getInstance().setActivity(getActivity()).listarProducto())
                .filtar(p -> p.getCantidad() > 0);
    }

}
