package com.business.ventas.utils;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.business.ventas.login.views.MainActivity;

public abstract class AppFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private MainActivity mainActivity;
    protected NavigationView navigationView;
    protected Toolbar toolbar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            mainActivity = (MainActivity) context;
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

    public void onButtonPressed(Fragment faFragment) {
        if (mListener != null) {
            mListener.onFragmentInteraction(faFragment);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Fragment faFragment);
    }

    public AppFragment setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
    }

    public AppFragment setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
        return this;
    }

    public MainActivity getMainActivity() {
        return mainActivity == null ? (MainActivity) getActivity() : mainActivity;
    }

}
