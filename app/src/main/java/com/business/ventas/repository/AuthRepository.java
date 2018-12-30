package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.User;

public interface AuthRepository {

    static AuthRepository newInstance(){
        return new AuthRepositoryImpl1();
    }

    AuthRepository signInWithEmailAndPassword(String Correo, String password, Context context);

    AuthRepository setOnCompleteSuscces(OnCompleteSuscces onCompleteSuscces);

    AuthRepository setOnCompleteError(OnCompleteError onCompleteError);

    User getUserSesion(Context context);

    void signOut(Context context);

    void addAuthStateListener(AuthStateListener authStateListener);

    void removeAuthStateListener(AuthStateListener authStateListener);

    @FunctionalInterface
    interface OnCompleteSuscces {
        void onComplete();
    }

    @FunctionalInterface
    interface OnCompleteError {
        void onComplete(String mensjae);
    }

    interface AuthStateListener {
        void onAuthStateChanged(boolean state);
    }

}
