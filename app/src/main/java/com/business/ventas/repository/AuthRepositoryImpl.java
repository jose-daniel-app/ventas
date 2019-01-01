package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.User;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.SharedPrefedenceCookies;

public class AuthRepositoryImpl implements AuthRepository {

    RepositoryFactory factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    UserRepository _user = factory.getUserRepository();

    OnCompleteSuscces onCompleteSuscces;
    OnCompleteError onCompleteError;

    private Lista<AuthStateListener> subscribers = new Lista<>();

    @Override
    public AuthRepository signInWithEmailAndPassword(String correo, String password, Context context) {
        _user.loginSesion(correo, password, new UserRepository.Respond<User>() {
            @Override
            public void succes(User obj) {
                onCompleteSuscces.onComplete();
                notificar(true);
            }
            @Override
            public void error(String cause) {
                onCompleteError.onComplete(cause);
            }
        }, context);
        return this;
    }

    @Override
    public User getUserSesion(Context context) {
        SharedPrefedenceCookies cookies = new SharedPrefedenceCookies(context);
        User user = new User().getUserCookies(cookies.getCookies());
        return user;
    }

    @Override
    public AuthRepository signOut(Context context) {
        _user.cerrarSeesion(context,mensaje -> {
            onCompleteSuscces.onComplete();
            notificar(false);
        }, error -> {
            onCompleteError.onComplete(error);
        });
        return this;
    }

    private void notificar(boolean state) {
        subscribers.foreach(item -> item.onAuthStateChanged(state));
    }

    @Override
    public AuthRepository setOnCompleteSuscces(OnCompleteSuscces onCompleteSuscces) {
        this.onCompleteSuscces = onCompleteSuscces;
        return this;
    }

    @Override
    public AuthRepository setOnCompleteError(OnCompleteError onCompleteError) {
        this.onCompleteError = onCompleteError;
        return this;
    }

    @Override
    public void addAuthStateListener(AuthStateListener authStateListener) {
        Context context = (Context) authStateListener;
        subscribers.add(authStateListener);
        _user.conseguirUsuarioRegistrado(context, resp -> {
            notificar(true);
        }, error -> {
            notificar(false);
        });
    }

    @Override
    public void removeAuthStateListener(AuthStateListener authStateListener) {
        subscribers.remove(authStateListener);
    }
}
