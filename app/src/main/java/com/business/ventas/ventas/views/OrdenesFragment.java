package com.business.ventas.ventas.views;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Ruta;
import com.business.ventas.login.views.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.viewAdapter.OrdenesViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class OrdenesFragment extends Fragment implements OnSearchToolbarQueryTextListner {

    VentasLog log = LogFactory.createInstance().setTag(OrdenesFragment.class.getSimpleName());
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
    OrdenesViewAdapter adapter;

    NavigationView navigationView;
    Toolbar toolbar;
    // FloatingActionButton idprueba;
    //CardView cardViewIdruta;
    //SearchToolbar searchToolbar;


    public OrdenesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdenesFragment.
     */

    public static OrdenesFragment newInstance(String param1, String param2) {
        OrdenesFragment fragment = new OrdenesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static OrdenesFragment newInstance() {
        return new OrdenesFragment();
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
        toolbar.setTitle("Ordenes");
        navigationView.setCheckedItem(R.id.nav_ventas);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.toolbar_requerimiento);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_date_range));
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        //searchToolbar = new SearchToolbar(getActivity(), this, getActivity().findViewById(R.id.search_layout));

        //idprueba = (FloatingActionButton) view.findViewById(R.id.idprueba);
        // idprueba.setOnClickListener(this::onClick);

        //     cardViewIdruta = (CardView) view.findViewById(R.id.cardViewIdruta);
        // cardViewIdruta.setOnClickListener(this::onClick);


        return view;

    }


    private void loadComponents(View view) {

        if (productlists.size() == 0) {
            productlists.add(new Ruta("SM00037F", "Urb. Los cedros de naranjal Mz B LT19","Lima","San Martin de Porres"));
            productlists.add(new Ruta("MF0038F", "An. Josè Pardo 1116","Lima","Miraflores"));
            productlists.add(new Ruta("BR0039F", "Jirón, Pichincha 485","Lima","Breña"));
            productlists.add(new Ruta("SM0040F", "Urb. Los cedros de naranjal Mz B LT19","Lima","San Martin de Porres"));

        }

        listarutas = view.findViewById(R.id.listaRuta);
        listarutas.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listarutas.setLayoutManager(linearLayoutManager);

        adapter = new OrdenesViewAdapter(productlists, this);
        listarutas.setAdapter(adapter);
        //adapter = new OrdenesViewAdapter(productlists, this);
     /*   adapter = OrdenesViewAdapter.newInstance().config()
                .setFragment(this).setListaRuta(productlists)
                .setListenerItem(codigoRuta -> {
                    DatePickerFragment newFragment = DatePickerFragment.newInstance().configEvent(fecha -> {
                        log.info("La fecha es: " + fecha + " codigoRuta: " + codigoRuta);
                        onButtonPressed(this);
                    });
                    newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
                }).build();


        listarutas.setAdapter(adapter);*/
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
/*
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idprueba:
                showDatePickerDialog();
                break;
        }
    }*/

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

    public OrdenesFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public OrdenesFragment setToolbar(Toolbar toolbar) {
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

