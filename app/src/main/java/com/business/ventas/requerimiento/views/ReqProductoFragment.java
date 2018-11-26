package com.business.ventas.requerimiento.views;

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
import com.business.ventas.viewAdapter.ReqProductoViewAdapter;
//import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.util.ArrayList;
import java.util.List;


public class ReqProductoFragment extends Fragment implements OnSearchToolbarQueryTextListner {
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
    ReqProductoViewAdapter adapter;

    NavigationView navigationView;
    Toolbar toolbar;
    SearchToolbarProducto searchToolbar;
    FloatingActionButton floatingActionButonContinuar;
    private SharedPreferenceProductos sharedProductos;

    public ReqProductoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reqproducto, container, false);
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
            listaPro.add(new Producto("Pastel", "sabor de vainilla con manjar blanco", R.drawable.pastel));
            listaPro.add(new Producto("Pastel", "sabor de vainilla con manjar blanco", R.drawable.pastel));
            listaPro.add(new Producto("Pastel", "sabor de vainilla con manjar blanco", R.drawable.pastel));
            listaPro.add(new Producto("Pastel", "sabor de vainilla con manjar blanco", R.drawable.pastel));
        }

        adapter = new ReqProductoViewAdapter(listaPro, getActivity());
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
     * @return A new instance of fragment ReqProductoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReqProductoFragment newInstance(String param1, String param2) {
        ReqProductoFragment fragment = new ReqProductoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static ReqProductoFragment newInstance() {
        return new ReqProductoFragment();
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

    public ReqProductoFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public ReqProductoFragment setToolbar(Toolbar toolbar) {
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
