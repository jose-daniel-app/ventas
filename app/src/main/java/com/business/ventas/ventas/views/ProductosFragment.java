package com.business.ventas.ventas.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.ProductoViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductosFragment extends Fragment implements OnSearchToolbarQueryTextListner {

    VentasLog log = LogFactory.createInstance().setTag(ProductosFragment.class.getSimpleName());

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView;
    ProductoViewAdapter adapter;

    NavigationView navigationView;
    Toolbar toolbar;
    SearchToolbarProducto searchToolbar;
    FloatingActionButton floatingActionButonContinuar;
    private SharedPreferenceProductos sharedProductos;

    public ProductosFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_productos, container, false);
        sharedProductos = SharedPreferenceProductos.getInstance().setActivity(getActivity());
        loadComponents(view);
        toolbar.setTitle(R.string.title_producto);
        navigationView.setCheckedItem(R.id.nav_ventas);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.productos_menu);
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        searchToolbar = new SearchToolbarProducto(getActivity(), this, getActivity().findViewById(R.id.search_producto));
        return view;

    }


    private boolean onMenuItemClick(MenuItem menuItem) {
        //onButtonPressed(this);
        switch (menuItem.getItemId()) {
            case R.id.ic_search:
                searchToolbar.openSearchToolbar();
                break;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadComponents(View view) {
        recyclerView = view.findViewById(R.id.listaPro);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mGridLayoutManager);
        floatingActionButonContinuar = view.findViewById(R.id.floatingActionButonContinuar);
        floatingActionButonContinuar.setOnClickListener(this::clickBtnContinuar);

        adapter = ProductoViewAdapter.newInstance().config()
            .setActivity(getActivity())
            .setProductlistAdap(this.listaDeProductos())
            .build();
        recyclerView.setAdapter(adapter);
    }

    private void clickBtnContinuar(View view) {
        new MaterialDialog.Builder(getActivity())
            .title(R.string.mensaje_compra)
            .positiveText(R.string.mensaje_ok).onPositive(this::onDialogOk)
            .negativeText(R.string.mensaje_cancelar)
            .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void onDialogOk(MaterialDialog dialog, DialogAction dialogAction) {
        sharedProductos.guardar(adapter.getProductlistAdap().stream().filter(p -> p.getPrecioCantidad() > 0).collect(Collectors.toList()));
        onButtonPressed(this);
    }

    public static ProductosFragment newInstance(String param1, String param2) {
        ProductosFragment fragment = new ProductosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static ProductosFragment newInstance() {
        return new ProductosFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onButtonPressed(Fragment fragment) {
        if (mListener != null) {
            mListener.onFragmentInteraction(fragment);
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

    public ProductosFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public ProductosFragment setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
    }

    public void onQueryTextSubmit(String query) {
        Toast.makeText(getActivity(), "User Query: " + query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryTextChange(String editable) {
        // textView.setText(editable);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Producto> listaDeProductos(){
        return new ArrayList<Producto>(){{
            add(new Producto(1, R.drawable.queque, "Keke", "sabor chocolate con chispas", 3.0));
            add(new Producto(2, R.drawable.pastel, "Pastel", "sabor de vainilla con manjar blanco", 3.0));
            add(new Producto(3, R.drawable.tinte, "desinfectante", "Limpia los baños y el labado", 3.0));
            add(new Producto(4, R.drawable.protect, "Quita grasa", "Quita toda la grasa del los patos y las cosas", 3.0));
            add(new Producto(5, R.drawable.arroz, "Arroz", "Arroz rompe olla para tu casa", 3.0));
            add(new Producto(6, R.drawable.orix, "Orix", "Orix, a la grasa le pone fin", 3.0));
        }}.stream().filter(p ->  {
            for(Producto producto : sharedProductos.listarProducto()){
                if(p.getCodigo() == producto.getCodigo()){
                    p.setCantidad(producto.getCantidad());
                    p.actualizarPrecioCantidad();
                }
            }
            return true;
        }).collect(Collectors.toList());
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Fragment fragment);
    }


}
