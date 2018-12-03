package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.User;

public interface UserRepository {

    void loginSesion(String correo, String password, Respond<User> listener, Context context);

    interface Respond<T> {
        void succes(T obj);
        void error(String cause);
    }
}
