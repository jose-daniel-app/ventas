package com.business.ventas.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.business.ventas.beans.User;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.SharedPrefedenceCookies;
import com.business.ventas.utils.VentasLog;

import java.util.ArrayList;
import java.util.List;

public class AuthRepositoryImpl {

    VentasLog log = LogFactory.createInstance().setTag(AuthRepositoryImpl.class.getSimpleName());

    RepositoryFactory _factory = RepositoryFactory.getFactory(RepositoryFactory.API_REST);
    UserRepository _userRepository = _factory.getUserRepository();
    OnCompleteSuscces onCompleteSuscces;
    OnCompleteError onCompleteError;

    private static final String API_KEY_REST = "api_key_rest";
    private static AuthRepositoryImpl authRepository;
    private List<AuthStateListener> subscribers = new ArrayList<>();


    private AuthRepositoryImpl() {
    }

    public static AuthRepositoryImpl getInstance() {
        if (authRepository == null)
            return new AuthRepositoryImpl();
        return authRepository;
    }

    public AuthRepositoryImpl signInWithEmailAndPassword(String Correo, String password, Context context) {
        _userRepository.loginSesion(Correo, password, new UserRepository.Respond<User>() {

            @Override
            public void succes(User obj) {
                logIn(obj.getApiKey(), context);
                if (onCompleteSuscces != null)
                    onCompleteSuscces.onComplete();
            }

            @Override
            public void error(String cause) {
                //listener.onComplete(false);
                if (onCompleteError != null)
                    onCompleteError.onComplete(cause);
            }
        },context);
        return this;
    }

    public void logIn(String apiKey, Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(API_KEY_REST, apiKey);
        editor.commit();
        notificar(true);
    }

    public void signOut(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(API_KEY_REST);
        editor.commit();
        notificar(false);
    }

    public String getApiKeyRest(Context context) {
        if (getSharedPreferences(context) != null) {
            return getSharedPreferences(context).getString(API_KEY_REST, null);
        }
        return null;
    }

    public User getUserSesion(Context context){
        SharedPrefedenceCookies cookies = new SharedPrefedenceCookies(context);
        User user = new User().getUserCookies(cookies.getCookies());
        return user;
    }

    public void addAuthStateListener(AuthStateListener authStateListener) {
        Context context = (Context) authStateListener;
        subscribers.add(authStateListener);
        //_userRepository.conseguirUsuarioRegistrado(context);
        notificar(getApiKeyRest(context) != null);

    }

    public void removeAuthStateListener(AuthStateListener authStateListener) {
        subscribers.remove(authStateListener);
    }

    SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("ventasPreferences", context.MODE_PRIVATE);
    }

    private void notificar(boolean state) {
        for (AuthStateListener subscriber : subscribers) {
            subscriber.onAuthStateChanged(state);
        }
    }

    public interface AuthStateListener {
        void onAuthStateChanged(boolean state);
    }

    public AuthRepositoryImpl setOnCompleteSuscces(OnCompleteSuscces onCompleteSuscces) {
        this.onCompleteSuscces = onCompleteSuscces;
        return this;
    }

    public AuthRepositoryImpl setOnCompleteError(OnCompleteError onCompleteError) {
        this.onCompleteError = onCompleteError;
        return this;
    }

    @FunctionalInterface
    public interface OnCompleteSuscces {
        void onComplete();
    }

    @FunctionalInterface
    public interface OnCompleteError {
        void onComplete(String mensjae);
    }

}
