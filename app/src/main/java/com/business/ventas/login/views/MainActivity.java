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
import com.business.ventas.requerimiento.views.DetalleFragment;
import com.business.ventas.requerimiento.views.ReqProductoFragment;
import com.business.ventas.requerimiento.views.RequerimientoFragment;
import com.business.ventas.requerimiento.views.RutaFragment;
import com.business.ventas.utils.AppFragmnet;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.ventas.views.BoletaFragment;
import com.business.ventas.ventas.views.ClienteFragment;
import com.business.ventas.ventas.views.DetalleGuiaFragment;
import com.business.ventas.ventas.views.OrdenesFragment;
import com.business.ventas.ventas.views.PedidoFragment;
import com.business.ventas.ventas.views.ProductosFragment;
import com.business.ventas.comprobante.views.ComprobanteFragment;


import java.util.List;

public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        // interfaz pare los fragment
        AppFragmnet.OnFragmentInteractionListener,
        //ClienteFragment.OnFragmentInteractionListener,
        ProductosFragment.OnFragmentInteractionListener,
        BoletaFragment.OnFragmentInteractionListener,
        ComprobanteFragment.OnFragmentInteractionListener,
        RequerimientoFragment.OnFragmentInteractionListener,
        ReqProductoFragment.OnFragmentInteractionListener,
        RutaFragment.OnFragmentInteractionListener,
        DetalleFragment.OnFragmentInteractionListener,
        DetalleGuiaFragment.OnFragmentInteractionListener,
        // interface para la sesion
        AuthRepository.AuthStateListener {

    AuthRepository auth = AuthRepository.getInstance();
    VentasLog log = LogFactory.createInstance().setTag(MainActivity.class.getSimpleName());

    NavigationView navigationView;
    Toolbar toolbar;
    //SearchToolbar searchToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_home);

        // toolbar.inflateMenu(R.menu.toolbar_menu);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        //toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        //menuFragment();
        showAppFragment(MenuFragment.newInstance());
        //pedidoFragment();
        //rutaFragment();
        //producto
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        toolbar.getMenu().clear();
        if (id == R.id.nav_home) {
            showAppFragment(MenuFragment.newInstance());
        } else if (id == R.id.nav_ventas) {
            showAppFragment(OrdenesFragment.newInstance());
        } else if (id == R.id.nav_vouchers) {
            comprobanteFragment();
        } else if (id == R.id.nav_requerimiento) {
            requerimientoFragment();
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

    private void productoFragment() {
        ProductosFragment productosFragment = ProductosFragment.newInstance()
                .setToolbar(toolbar)
                .setNavigationView(navigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, productosFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    /*------------------------------------------------*/
    public void boletaFragment() {
        BoletaFragment boletaFragment = BoletaFragment.newInstance()
                .setToolbar(toolbar)
                .setNavigationView(navigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, boletaFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    public void boletaFragment(String titulo) {
        BoletaFragment boletaFragment = BoletaFragment.newInstance()
                .setToolbar(toolbar)
                .setTitulo(titulo)
                .setNavigationView(navigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, boletaFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    /*--------------------Comprobante---------------------------*/
    private void comprobanteFragment() {
        ComprobanteFragment comprobanteFragment = ComprobanteFragment.newInstance()
                .setToolbar(toolbar)
                .setNavigationView(navigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, comprobanteFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    /*--------------------Requerimiento---------------------------*/
    private void requerimientoFragment() {
        RequerimientoFragment requerimientoFragment = RequerimientoFragment.newInstance()
                .setToolbar(toolbar)
                .setNavigationView(navigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, requerimientoFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    /*--------------------ReqProductoFragment---------------------------*/
    private void reqProductoFragment() {
        ReqProductoFragment reqProductoFragment = ReqProductoFragment.newInstance()
                .setToolbar(toolbar)
                .setNavigationView(navigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, reqProductoFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    private void rutaFragment() {
        RutaFragment rutaFragment = RutaFragment.newInstance()
                .setToolbar(toolbar)
                .setNavigationView(navigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, rutaFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    private void detalleFragment() {
        DetalleFragment detalleFragment = DetalleFragment.newInstance()
                .setToolbar(toolbar)
                .setNavigationView(navigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, detalleFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    private void pedidoFragment() {
        PedidoFragment pedidoFragment = PedidoFragment.newInstance()
                .setToolbar(toolbar)
                .setNavigationView(navigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, pedidoFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    public void detalleGuiaFragment() {
        DetalleGuiaFragment detalleGuiaFragment = DetalleGuiaFragment.newInstance()
                .setToolbar(toolbar)
                .setNavigationView(navigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, detalleGuiaFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    /*private void ordenesFragment() {
        OrdenesFragment ordenesFragment = OrdenesFragment.newInstance()
                .setToolbar(toolbar)
                .setNavigationView(navigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, ordenesFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }*/

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
            //executeActionMenuFragment(fragment);
        } else if (faFragment instanceof ProductosFragment) {
            ProductosFragment fragment = castFragment(ProductosFragment.class, faFragment);
            executeActionProductosFragment(fragment);

        } else if (faFragment instanceof ClienteFragment) {
            ClienteFragment fragment = castFragment(ClienteFragment.class, faFragment);
            executeActionClienteFragment(faFragment);
        } else if (faFragment instanceof OrdenesFragment) {
            OrdenesFragment fragment = castFragment(OrdenesFragment.class, faFragment);
            executeActionOrdenesFragment(faFragment);
        } else if (faFragment instanceof RutaFragment) {
            RutaFragment fragment = castFragment(RutaFragment.class, faFragment);
            executeActionRutaFragment(fragment);
        } else if (faFragment instanceof ComprobanteFragment) {
            ComprobanteFragment fragment = castFragment(ComprobanteFragment.class, faFragment);
            executeActionComprobanteFragment(fragment);
        } else if (faFragment instanceof RequerimientoFragment) {
            RequerimientoFragment fragment = castFragment(RequerimientoFragment.class, faFragment);
            executeActioRequerimientoFragment(fragment);
        } else if (faFragment instanceof DetalleGuiaFragment) {
            DetalleGuiaFragment fragment = castFragment(DetalleGuiaFragment.class, faFragment);
            executeActionDetalleGuiaFragment(fragment);
        } else if (faFragment instanceof PedidoFragment) {
            PedidoFragment fragment = castFragment(PedidoFragment.class, faFragment);
            executeActionPedidoFragment(fragment);
        }
    }

    private void executeActionPedidoFragment(PedidoFragment fragment) {
        //TODO
        detalleGuiaFragment();
    }

    private void executeActionDetalleGuiaFragment(DetalleGuiaFragment fragment) {
        //TODO
    }

    private void executeActioRequerimientoFragment(RequerimientoFragment fragment) {
        switch (fragment.getTipoAccion()) {
            case RequerimientoFragment.CREAR_REQUERIMIENTO:
                rutaFragment();
                break;
            case RequerimientoFragment.DETALLE_REQUERIMIENTO:
                boletaFragment();
                break;
            default:
        }

    }

    private void executeActionComprobanteFragment(ComprobanteFragment fragment) {
        boletaFragment();
    }

    private void executeActionRutaFragment(RutaFragment fragment) {
        productoFragment();
    }

    private void executeActionClienteFragment(Fragment faFragment) {
        productoFragment();
    }

    private void executeActionProductosFragment(ProductosFragment fragment) {
        //boletaFragment();
        pedidoFragment();
    }

    private void executeActionRequerimientoFragment(Fragment faFragment) {

        rutaFragment();
    }

    private void executeActionOrdenesFragment(Fragment fragment) {

        //clienteFragment();
    }

    /*private void executeActionMenuFragment(MenuFragment fragment) {
        switch (fragment.getPressTheItemType()) {
            case MenuFragment.PRESS_ITEM_VENTAS:
                ordenesFragment();
                break;
            case MenuFragment.PRESS_ITEM_REQUER:
                requerimientoFragment();
                break;
            case MenuFragment.PRESS_ITEM_COMPRO:
                comprobanteFragment();
                break;
            case MenuFragment.PRESS_ITEM_SALIR:
                auth.signOut(this);
                break;
            default:
        }
    }*/

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
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private <T> T castFragment(Class<T> clas, Fragment fragment) {
        return (T) fragment;
    }

    public FragmentHandler newFragmentHandler() {
        return new FragmentHandler(this);
    }

    private void showAppFragment(AppFragmnet fragmnet) {
        newFragmentHandler().changeFragment(fragmnet);
    }

    public class FragmentHandler {
        MainActivity mainActivity;

        public FragmentHandler(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        public FragmentHandler changeFragment(AppFragmnet fragment) {
            fragment.setNavigationView(this.mainActivity.navigationView)
                    .setToolbar(this.mainActivity.toolbar);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null).commit();
            return this;
        }
    }
}
