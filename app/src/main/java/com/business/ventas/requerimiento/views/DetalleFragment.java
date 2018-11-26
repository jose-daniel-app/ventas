package com.business.ventas.requerimiento.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.business.ventas.R;
import com.business.ventas.utils.AppFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetalleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleFragment extends AppFragment {

    public DetalleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        toolbar.setTitle(R.string.title_detalle);
        navigationView.setCheckedItem(R.id.nav_ventas);
        toolbar.getMenu().clear();
        return view;
    }

    public static DetalleFragment newInstance() {
        return new DetalleFragment();
    }

}
