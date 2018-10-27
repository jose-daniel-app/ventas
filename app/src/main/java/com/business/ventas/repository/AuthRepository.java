package com.business.ventas.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

import java.util.ArrayList;
import java.util.List;

public class AuthRepository {

    VentasLog log = LogFactory.createInstance().setTag(AuthRepository.class.getSimpleName());

    private static final String API_KEY_REST = "api_key_rest";
    private static AuthRepository authRepository;
    private static final List<AuthStateListener> subscribers = new ArrayList<>();

    private AuthRepository() {
    }

    public static AuthRepository getInstance(){
        if(authRepository == null)
            return new AuthRepository();
        return authRepository;
    }


    public void addKey(String apiKey, Context context){

        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(API_KEY_REST, apiKey);
        editor.commit();
        notificar(true);
    }


    public void deleteKey(Context context){
        SharedPreferences sharedPreferences = getSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(API_KEY_REST);
            editor.commit();
            notificar(false);
    }

    public String getApiKeyRest(Context context){
        if(getSharedPreferences(context) != null){
            return getSharedPreferences(context).getString(API_KEY_REST,null);
        }
        return null;
    }


    public void addAuthStateListener(AuthStateListener authStateListener){
        subscribers.add(authStateListener);
        notificar(getApiKeyRest((Context)authStateListener) != null);
    }

    public void removeAuthStateListener(AuthStateListener authStateListener){
        subscribers.remove(authStateListener);
    }

    SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences("ventasPreferences", context.MODE_PRIVATE);
    }

    private void notificar(boolean state){
        for (AuthStateListener subscriber : subscribers){
            subscriber.onAuthStateChanged(state);
        }
    }


    public interface AuthStateListener {
        public void onAuthStateChanged(boolean state);
    }

}
