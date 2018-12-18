package com.business.ventas.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.business.ventas.apiRest.RestApiAdapter;

import java.util.HashSet;

public class SharedPrefedenceCookies {
    private VentasLog log = LogFactory.createInstance().setTag(SharedPrefedenceCookies.class.getSimpleName());
    private static final String PREF_COOKIES = "PREF_COOKIES";
    private Context context;

    public SharedPrefedenceCookies(Context context) {
        this.context = context;
    }

    private SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void guardarCokkies(HashSet<String> cookies) {
        SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(context).edit();
        memes.putStringSet(PREF_COOKIES, cookies).apply();
        memes.commit();
    }

    public AppHashSet<String> getCookies() {
        return new AppHashSet<>(this.getPreferences().getStringSet(PREF_COOKIES, new HashSet<String>()));
    }

    public String concatenateCookies(ConcatenateCookies concatenateCookies) {
        String cookiestring = "";
        for (String cookie : this.getCookies()) {
            cookiestring = cookiestring + concatenateCookies.concatenate(cookie);
        }
        return cookiestring;
    }

    @FunctionalInterface
    public interface ConcatenateCookies {
        String concatenate(String item);
    }

}
