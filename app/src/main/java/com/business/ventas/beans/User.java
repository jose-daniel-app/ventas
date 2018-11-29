package com.business.ventas.beans;

import com.business.ventas.utils.AppHashSet;

public class User {
    private String nombres;
    private String Correo;
    private String password;
    private String apiKey;

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public User getUserCookies(AppHashSet<String>  cookies){
        User user = new User();
        //user.set
        return user;
    }

    private String getUser_id(AppHashSet<String>  cookies){
        return null;
    }

}
