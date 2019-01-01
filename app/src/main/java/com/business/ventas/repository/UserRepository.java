package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.User;
import com.business.ventas.utils.IpadreRepository;

public interface UserRepository extends IpadreRepository {

    void loginSesion(String correo, String password, Respond<User> listener, Context context);

    void conseguirUsuarioRegistrado(Context context,RespuestaSucces<String> succes, RespuestaError error);

    void cerrarSeesion(Context context,RespuestaSucces<String> succes, RespuestaError error);

    interface Respond<T> {
        void succes(T obj);

        void error(String cause);
    }
}
