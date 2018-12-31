package com.business.ventas.login.views;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.business.ventas.R;
import com.business.ventas.beans.User;
import com.business.ventas.repository.AuthRepository;
import com.business.ventas.requerimiento.views.RequerimientoFragment;
import com.business.ventas.utils.AppFragment;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.ordenes.views.OrdenesFragment;
import com.business.ventas.comprobante.views.ComprobanteFragment;


import java.util.List;

public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        // interfaz pare los fragment
        AppFragment.OnFragmentInteractionListener,
        // interface para la sesion
        AuthRepository.AuthStateListener {

    AuthRepository auth = AuthRepository.newInstance();
    VentasLog log = LogFactory.createInstance().setTag(MainActivity.class.getSimpleName());

    ActivityAconKeyDown activityAconKeyDown;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView txtNombre;
    TextView txtCorreo;
    ImageView imageViewAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_home);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        User user = auth.getUserSesion(this);
        txtNombre = navigationView.getHeaderView(0).findViewById(R.id.txtNombre);
        txtCorreo = navigationView.getHeaderView(0).findViewById(R.id.txtCorreo);
        imageViewAvatar = navigationView.getHeaderView(0).findViewById(R.id.imageViewAvatar);

        txtNombre.setText(user.getFullName());
        txtCorreo.setText(user.getCorreo());
        Glide.with(this).load(user.getPathImg()).into(imageViewAvatar);
        showAppFragment(MenuFragment.newInstance());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        toolbar.getMenu().clear();
        if (id == R.id.nav_home) {
            showAppFragment(MenuFragment.newInstance());
        } else if (id == R.id.nav_ordenes) {
            showAppFragment(OrdenesFragment.newInstance());
        } else if (id == R.id.nav_vouchers) {
            showAppFragment(ComprobanteFragment.newInstance());
        } else if (id == R.id.nav_requerimiento) {
            showAppFragment(RequerimientoFragment.newInstance());
        } else if (id == R.id.nav_cerrar_session) {
            cerrarSesion();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cerrarSesion() {
        auth.signOut(this).setOnCompleteSuscces(()->{

        }).setOnCompleteError(mensaje -> {

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(this);
    }

    public void setActivityAconKeyDown(ActivityAconKeyDown activityAconKeyDown) {
        this.activityAconKeyDown = activityAconKeyDown;
    }

    @Override
    public void onFragmentInteraction(Fragment faFragment) {
    }


    @Override
    public void onAuthStateChanged(boolean state) {
        if (!state) {
            Toast.makeText(this, "Su sesión a expirado", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList != null) {
            for (Fragment fragment : fragmentList) {
                if (fragment instanceof MenuFragment) {
                    finish();
                }
            }
        }
        if (this.activityAconKeyDown != null)
            this.activityAconKeyDown.onKeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    public FragmentHandler newFragmentHandler() {
        return new FragmentHandler(this);
    }

    private void showAppFragment(AppFragment fragmnet) {
        newFragmentHandler().changeFragment(fragmnet);
    }

    public class FragmentHandler {
        MainActivity mainActivity;

        public FragmentHandler(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        public FragmentHandler changeFragment(AppFragment fragment) {
            fragment.setNavigationView(this.mainActivity.navigationView)
                    .setToolbar(this.mainActivity.toolbar);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null).commit();
            return this;
        }
    }

    public interface ActivityAconKeyDown {
        void onKeyDown(int keyCode, KeyEvent event);
    }

}
