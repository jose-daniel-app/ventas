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

import com.business.ventas.R;
import com.business.ventas.repository.AuthRepository;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.ventas.views.BoletaFragment;
import com.business.ventas.ventas.views.ClienteFragment;
import com.business.ventas.ventas.views.ProductosFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        // interfaz pare los fragment
        MenuFragment.OnFragmentInteractionListener,
        ClienteFragment.OnFragmentInteractionListener,
        ProductosFragment.OnFragmentInteractionListener,
        BoletaFragment.OnFragmentInteractionListener,
        // interface para la sesion
        AuthRepository.AuthStateListener {

    AuthRepository auth = AuthRepository.getInstance();
    VentasLog log = LogFactory.createInstance().setTag(MainActivity.class.getSimpleName());

    NavigationView navigationView;
    Toolbar toolbar;

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
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        menuFragment();
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
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.referencia, menu);
        //getMenuInflater().inflate(R.menu.searchfile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        toolbar.getMenu().clear();
        if (id == R.id.nav_home) {
            menuFragment();
        } else if (id == R.id.nav_ventas) {
            clienteFragment();
        } else if (id == R.id.nav_vouchers) {

        } else if (id == R.id.nav_requerimiento) {

        } else if (id == R.id.nav_cerrar_session) {
            cerrarSesion();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cerrarSesion() {
        auth.signOut(this);
    }

    private void menuFragment() {
        getSupportActionBar().setTitle(R.string.title_home);
        MenuFragment menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    private void clienteFragment() {
        getSupportActionBar().setTitle(R.string.title_ventas);
        ClienteFragment clienteFragment = new ClienteFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, clienteFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    private void productoFragment() {
        getSupportActionBar().setTitle(R.string.title_producto);
        ProductosFragment productosFragment = new ProductosFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, productosFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    private void boletaFragment() {
        getSupportActionBar().setTitle(R.string.title_boleta);
        BoletaFragment boletaFragment = new BoletaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, boletaFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
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

    @Override
    public void onFragmentInteraction(Fragment faFragment) {
        if (faFragment instanceof MenuFragment) {
            MenuFragment fragment = castFragment(MenuFragment.class, faFragment);
            executeActionMenuFragment(fragment);
        } else if (faFragment instanceof ProductosFragment) {
            ProductosFragment fragment = castFragment(ProductosFragment.class, faFragment);
            executeActionProductosFragment(fragment);
        } else if (faFragment instanceof ClienteFragment) {
            ClienteFragment fragment = castFragment(ClienteFragment.class, faFragment);
            executeActionClienteFragment(faFragment);
        }
    }

    private void executeActionClienteFragment(Fragment faFragment) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.productos_menu);
        productoFragment();
    }

    private void executeActionProductosFragment(ProductosFragment fragment) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.boleta_menu);
        boletaFragment();
    }

    private void executeActionMenuFragment(MenuFragment fragment) {
        switch (fragment.getPressTheItemType()) {
            case MenuFragment.PRESS_ITEM_VENTAS:
                navigationView.setCheckedItem(R.id.nav_ventas);
                clienteFragment();
                break;
            case MenuFragment.PRESS_ITEM_REQUER:
                break;
            case MenuFragment.PRESS_ITEM_COMPRO:
                break;
            case MenuFragment.PRESS_ITEM_SALIR:
                auth.signOut(this);
                break;
            default:
        }

    }

    @Override
    public void onAuthStateChanged(boolean state) {
        if (!state) {
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
                } else if (fragment instanceof ClienteFragment) {
                    getSupportActionBar().setTitle(R.string.title_home);
                    navigationView.setCheckedItem(R.id.nav_home);
                } else if (fragment instanceof ProductosFragment) {
                    getSupportActionBar().setTitle(R.string.title_ventas);
                    toolbar.getMenu().clear();
                } else if (fragment instanceof BoletaFragment) {
                    getSupportActionBar().setTitle(R.string.title_producto);
                    toolbar.getMenu().clear();
                    toolbar.inflateMenu(R.menu.productos_menu);
                }
            }

        }

        return super.onKeyDown(keyCode, event);
    }

    private boolean onMenuItemClick(MenuItem item) {
        //TODO
        log.info("se esta click");
        int id = item.getItemId();
        switch (id) {
            case R.id.item_check_producto:
                getSupportActionBar().setTitle(R.string.title_producto);
                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.boleta_menu);
                boletaFragment();
                break;
        }

        return false;
    }

    private <T> T castFragment(Class<T> clas, Fragment fragment) {
        return (T) fragment;
    }
}
