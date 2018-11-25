package com.business.ventas.ventas.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.viewAdapter.ItemPedidosBaseAdapter;
import com.github.clans.fab.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PedidoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PedidoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PedidoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    NavigationView navigationView;
    Toolbar toolbar;
    ListView listViewItem;
    ItemPedidosBaseAdapter adapter;

    FloatingActionButton item1;

    public PedidoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pedido, container, false);
        loadComponents(view);
        return view;
    }

    private void loadComponents(View view) {
        toolbar.setTitle(R.string.title_orden);
        navigationView.setCheckedItem(R.id.nav_ventas);
        toolbar.getMenu().clear();
        listViewItem = view.findViewById(R.id.listViewItem);
        item1 = view.findViewById(R.id.item1);
        item1.setOnClickListener(this::clickItem);
        adapter = new ItemPedidosBaseAdapter(getActivity(), R.layout.view_item_pedido, listaProducos());
        listViewItem.setAdapter(adapter);
        // cagar los componentes del layout
    }

    private void clickItem(View view) {
        onButtonPressed(this);
    }

    public static PedidoFragment newInstance(String param1, String param2) {
        PedidoFragment fragment = new PedidoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static PedidoFragment newInstance() {
        return new PedidoFragment();
    }

    public PedidoFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public PedidoFragment setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    public void onButtonPressed(Fragment fragmnet) {
        if (mListener != null) {
            mListener.onFragmentInteraction(fragmnet);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private Lista<Producto> listaProducos() {
        return new Lista<Producto>(SharedPreferenceProductos.getInstance().setActivity(getActivity()).listarProducto())
                .filtar(p -> p.getCantidad() > 0);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Fragment fragmnet);
    }
}
