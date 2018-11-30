package com.business.ventas.beans;

import com.business.ventas.apiRest.Constants;
import com.business.ventas.utils.AppHashSet;

import java.net.URI;

public class User {

    private String fullName;
    private String pathImg;
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

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public User getUserCookies(AppHashSet<String> cookies) {
        cookies.foreach(item -> {
            if (item.contains("full_name"))
                setFullName(parserFullName(item));
            else if (item.contains("user_id"))
                setCorreo(parserUserId(item));
            else if (item.contains("user_image"))
                setPathImg(parserUserImage(item));
        });
        return this;
    }

    private String parserUserImage(String item) {
        return Constants.URL_ROOT + item.split("=")[1]
                .replaceAll("Path", "")
                .replaceAll(";", "")
                .trim();
    }

    private String parserUserId(String item) {
        return item.split("=")[1]
                .replaceAll("Path", "")
                .replaceAll(";", "")
                .replaceAll("%40", "@")
                .trim();
    }

    private String parserFullName(String item) {
        return item.split("=")[1]
                .replaceAll("Path", "")
                .replaceAll(";", "")
                .replaceAll("%20", " ")
                .trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", pathImg='" + pathImg + '\'' +
                ", Correo='" + Correo + '\'' +
                ", password='" + password + '\'' +
                ", apiKey='" + apiKey + '\'' +
                '}';
    }
}
