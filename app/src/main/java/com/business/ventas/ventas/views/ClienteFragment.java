package com.business.ventas.ventas.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.business.ventas.R;
import com.business.ventas.beans.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClienteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClienteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView listaclientes;
    List<Cliente> productlists = new ArrayList<>();
    ClienteActivityAdapter adapter;

    public ClienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClienteFragment newInstance(String param1, String param2) {
        ClienteFragment fragment = new ClienteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);
        loadComponents(view);
        return view;
    }

    private void loadComponents(View view) {

        productlists.add(new Cliente("Ana Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Beto Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Carlos Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("David Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Eduardo Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Fernando Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Giovanni Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Helena Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Isabel Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));
        productlists.add(new Cliente("Jose Nombre Apellido ", R.drawable.logobase1,"104593895087","SMP, urb. los cedros de naranjal"));

        listaclientes = view.findViewById(R.id.listacli);
        listaclientes.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listaclientes.setLayoutManager(linearLayoutManager);

        adapter = new ClienteActivityAdapter(productlists, getActivity());
        listaclientes.setAdapter(adapter);
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
}
