package com.business.ventas.repository;

import com.business.ventas.beans.User;

public interface UserRepository {

    void loginSesion(String correo, String password, Respond<User> listener);

    interface Respond<T> {
        void succes(T obj);
        void error(String cause);
    }
}
