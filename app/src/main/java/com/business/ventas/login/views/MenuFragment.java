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
import com.business.ventas.utils.AppFragmnet;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.login.views.SearchToolbar.OnSearchToolbarQueryTextListner;
import com.business.ventas.ventas.views.ClienteFragment;
import com.business.ventas.ventas.views.OrdenesFragment;
import com.business.ventas.viewAdapter.MenuItemViewAdapter;
import com.business.ventas.viewAdapter.ProductoViewAdapter;

import java.util.ArrayList;

public class MenuFragment extends AppFragmnet {

    VentasLog log = LogFactory.createInstance().setTag(MenuFragment.class.getSimpleName());
    RecyclerView recyclerViewItems;
    MenuItemViewAdapter menuItemViewAdapter;

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
            add(new MenuItemViewAdapter.Elemento().setId(1).setImagen(R.drawable.ic_menu_ventas).setDescripcion("Ordenes"));
            add(new MenuItemViewAdapter.Elemento().setId(2).setImagen(R.drawable.ic_menu_vouchers).setDescripcion("Comprobantes"));
            add(new MenuItemViewAdapter.Elemento().setId(3).setImagen(R.drawable.ic_menu_assignment).setDescripcion("Requerimientos"));
            add(new MenuItemViewAdapter.Elemento().setId(4).setImagen(R.drawable.ic_menu_exit).setDescripcion("Salir"));
        }}, obj -> {
            switch (obj.getId()){
                case 1: getMainActivity().newFragmentHandler().changeFragment(OrdenesFragment.newInstance());break;
                case 2: getMainActivity().newFragmentHandler().changeFragment(ClienteFragment.newInstance());break;
                case 3: getMainActivity().newFragmentHandler().changeFragment(ClienteFragment.newInstance());break;
                case 4: getMainActivity().newFragmentHandler().changeFragment(ClienteFragment.newInstance());break;
                default:
            }
        });
        recyclerViewItems.setAdapter(menuItemViewAdapter);
    }


    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

 }
