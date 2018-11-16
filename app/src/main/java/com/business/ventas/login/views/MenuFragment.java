package com.business.ventas.login.views;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.login.views.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.viewAdapter.MenuItemViewAdapter;
import com.business.ventas.viewAdapter.ProductoViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    VentasLog log = LogFactory.createInstance().setTag(MenuFragment.class.getSimpleName());
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final int PRESS_ITEM_VENTAS = 0;
    public static final int PRESS_ITEM_REQUER = 1;
    public static final int PRESS_ITEM_COMPRO = 2;
    public static final int PRESS_ITEM_SALIR = 3;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int typeItem;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerViewItems;
    NavigationView navigationView;
    Toolbar toolbar;

    MenuItemViewAdapter menuItemViewAdapter;


    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        loadComponents(view);
        toolbar.setTitle(R.string.title_home);
        toolbar.getMenu().clear();
        navigationView.setCheckedItem(R.id.nav_home);
        return view;
    }

    private void loadComponents(View view) {
        recyclerViewItems = view.findViewById(R.id.recyclerViewItems);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewItems.setLayoutManager(mGridLayoutManager);
        menuItemViewAdapter = new MenuItemViewAdapter(new ArrayList<MenuItemViewAdapter.Elemento>() {{
            add(new MenuItemViewAdapter.Elemento().setId(PRESS_ITEM_VENTAS).setImagen(R.drawable.ic_menu_ventas).setDescripcion("Ventas"));
            add(new MenuItemViewAdapter.Elemento().setId(PRESS_ITEM_REQUER).setImagen(R.drawable.ic_menu_vouchers).setDescripcion("Comprobante"));
            add(new MenuItemViewAdapter.Elemento().setId(PRESS_ITEM_COMPRO).setImagen(R.drawable.ic_menu_assignment).setDescripcion("Requerimiento"));
            add(new MenuItemViewAdapter.Elemento().setId(PRESS_ITEM_SALIR).setImagen(R.drawable.ic_menu_exit).setDescripcion("Salir"));
        }}, this::onclickItem);
        recyclerViewItems.setAdapter(menuItemViewAdapter);
    }

    private void onclickItem(MenuItemViewAdapter.Elemento elemento) {
        int id = elemento.getId();
        this.typeItem = id;
        switch (id) {
            case PRESS_ITEM_VENTAS:
                onButtonPressed(this);
                break;
            case PRESS_ITEM_REQUER:
                onButtonPressed(this);
                break;
            case PRESS_ITEM_COMPRO:
                onButtonPressed(this);
                break;
            case PRESS_ITEM_SALIR:
                onButtonPressed(this);
                break;
            default:
                log.info("entro al default");
        }
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
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

    public int getPressTheItemType() {
        return typeItem;
    }


    public MenuFragment setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
    }

    public MenuFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
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

}
