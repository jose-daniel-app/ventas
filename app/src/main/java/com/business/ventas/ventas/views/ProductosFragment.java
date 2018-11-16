package com.business.ventas.ventas.views;

import android.content.Context;
import android.os.Bundle;
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

import com.afollestad.materialdialogs.MaterialDialog;
import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.login.views.SearchToolbarProducto;
import com.business.ventas.login.views.SearchToolbarProducto.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.viewAdapter.ProductoViewAdapter;
//import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductosFragment extends Fragment implements OnSearchToolbarQueryTextListner {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView;
    List<Producto> listaPro = new ArrayList<>();
    ProductoViewAdapter adapter;

    NavigationView navigationView;
    Toolbar toolbar;
    SearchToolbarProducto searchToolbar;
    FloatingActionButton floatingActionButonContinuar;
    private SharedPreferenceProductos sharedProductos;

    public ProductosFragment() {
        // Required empty public constructor
    }

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

    private void loadComponents(View view) {
        recyclerView = view.findViewById(R.id.listaPro);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mGridLayoutManager);
        floatingActionButonContinuar = view.findViewById(R.id.floatingActionButonContinuar);
        floatingActionButonContinuar.setOnClickListener(this::clickBtnContinuar);

        if (listaPro.size() == 0) {
            listaPro.add(new Producto("Keke", "sabor chocolate con chispas", R.drawable.queque));
            listaPro.add(new Producto("Pastel", "sabor de vainilla con manjar blanco", R.drawable.pastel));
            listaPro.add(new Producto("desinfectante", "Limpia los baños y el labado", R.drawable.tinte));
            listaPro.add(new Producto("Quita grasa", "Quita toda la grasa del los patos y las cosas", R.drawable.protect));
            listaPro.add(new Producto("Arroz", "Arroz rompe olla para tu casa", R.drawable.arroz));
            listaPro.add(new Producto("Orix", "Orix, a la grasa le pone fin", R.drawable.orix));
        }

        adapter = new ProductoViewAdapter(listaPro, getActivity());
        recyclerView.setAdapter(adapter);
    }

    private void clickBtnContinuar(View view) {
        new MaterialDialog.Builder(getActivity())
            .title(R.string.mensaje_compra)
            .positiveText(R.string.mensaje_ok).onPositive((dialog, which)->{ onButtonPressed(this); })
            .negativeText(R.string.mensaje_cancelar)
            .show();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductosFragment.
     */
    // TODO: Rename and change types and number of parameters
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


    // TODO: Rename method, update argument and hook method into UI event
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


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Fragment fragment);
    }


}
