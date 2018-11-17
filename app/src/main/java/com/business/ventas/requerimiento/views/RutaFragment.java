package com.business.ventas.requerimiento.views;


import com.business.ventas.login.views.MainActivity;
import com.business.ventas.login.views.SearchToolbar;

import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Ruta;
import com.business.ventas.login.views.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.RutaViewAdapter;


import java.util.ArrayList;
import java.util.List;


public class RutaFragment extends Fragment implements OnSearchToolbarQueryTextListner {

    VentasLog log = LogFactory.createInstance().setTag(RutaFragment.class.getSimpleName());
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView listarutas;
    List<Ruta> productlists = new ArrayList<>();
    RutaViewAdapter adapter;

    NavigationView navigationView;
    Toolbar toolbar;
    FloatingActionButton idprueba;
    //CardView cardViewIdruta;
    //SearchToolbar searchToolbar;


    public RutaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RutaFragment.
     */

    public static RutaFragment newInstance(String param1, String param2) {
        RutaFragment fragment = new RutaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static RutaFragment newInstance() {
        return new RutaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ruta, container, false);
        loadComponents(view);
        toolbar.setTitle(R.string.title_ruta);
        navigationView.setCheckedItem(R.id.nav_ventas);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_ruta);

        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        //searchToolbar = new SearchToolbar(getActivity(), this, getActivity().findViewById(R.id.search_layout));

        idprueba = (FloatingActionButton) view.findViewById(R.id.idprueba);
        idprueba.setOnClickListener(this::onClick);

        //     cardViewIdruta = (CardView) view.findViewById(R.id.cardViewIdruta);
        // cardViewIdruta.setOnClickListener(this::onClick);


        return view;

    }


    private void loadComponents(View view) {

        if (productlists.size() == 0) {
            productlists.add(new Ruta("MF0037F", "SMP, Urb. Los cedros de naranjal Mz B LT19"));
            productlists.add(new Ruta("MF0038F", "SMP, Urb. Los cedros de naranjal Mz B LT19"));
            productlists.add(new Ruta("MF0039F", "SMP, Urb. Los cedros de naranjal Mz B LT19"));
            productlists.add(new Ruta("MF0040F", "SMP, Urb. Los cedros de naranjal Mz B LT19"));

        }

        listarutas = view.findViewById(R.id.listaRuta);
        listarutas.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listarutas.setLayoutManager(linearLayoutManager);

        //adapter = new RutaViewAdapter(productlists, this);
        adapter = RutaViewAdapter.newInstance().config()
                .setFragment(this).setListaRuta(productlists)
                .setListenerItem(codigoRuta -> {
                    DatePickerFragment newFragment = DatePickerFragment.newInstance().configEvent(fecha -> {
                        log.info("La fecha es: " + fecha + " codigoRuta: " + codigoRuta);
                        onButtonPressed(this);
                    });
                    newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
                }).build();


        listarutas.setAdapter(adapter);
    }


    private boolean onMenuItemClick(MenuItem menuItem) {
        //onButtonPressed(this);

     /*   switch (menuItem.getItemId()) {
            case R.id.ic_search_time:
              //  searchToolbar.openSearchToolbar();
                break;
        }*/

        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idprueba:
                showDatePickerDialog();
                break;
        }
    }

    private void showDatePickerDialog() {
        //DatePickerFragment newFragment = new DatePickerFragment();
        //DatePickerFragment newFragment = DatePickerFragment.newInstance().configEvent(fecha -> {
         //   log.info("La fecha es: " + fecha);
        //});
        //newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }


    // TODO: Renombrar método, actualizar argumento y enganchar método en evento UI
    public void onButtonPressed(Fragment faFragment) {
        if (mListener != null) {
            mListener.onFragmentInteraction(faFragment);
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

    public RutaFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public RutaFragment setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
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
        void onFragmentInteraction(Fragment faFragment);
    }

    public void onQueryTextSubmit(String query) {
        Toast.makeText(getActivity(), "User Query: " + query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryTextChange(String editable) {
        // textView.setText(editable);
    }

}

