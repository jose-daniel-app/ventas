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
    private static SharedPreferences sharedPreferences;
    private List<AuthStateListener> clients = new ArrayList<>();

    private AuthRepository() {
    }

    public static AuthRepository getInstance(){
        if(authRepository == null)
            return new AuthRepository();
        return authRepository;
    }


    public void addKey(String apiKey, Context context){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("ventasPreferences", context.MODE_PRIVATE);
        }

        if(getApiKeyRest() == null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(API_KEY_REST, apiKey);
            editor.commit();
        }
        notificar(true);
    }


    public void deleteKey(){
        if(sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(API_KEY_REST);
            editor.commit();
            notificar(false);
        }

    }

    public static String getApiKeyRest(){
        if(sharedPreferences != null){
            return sharedPreferences.getString(API_KEY_REST,null);
        }
        return null;
    }


    public void addAuthStateListener(AuthStateListener authStateListener){
        clients.add(authStateListener);
        notificar(getApiKeyRest() != null);
    }

    public void removeAuthStateListener(AuthStateListener authStateListener){
        clients.remove(authStateListener);
    }


    private void notificar(boolean state){
        log.info("Se esta iniciando el login : " + state);
        for (AuthStateListener listener : clients ){
            listener.onAuthStateChanged(state);
        }
    }


    public interface AuthStateListener {
        public void onAuthStateChanged(boolean state);
    }

}
